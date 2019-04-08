package com.example.cyparking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    private static String userToken = "";
    public static String getToken() {
        return userToken;
    }
    public static void setToken(String newToken) {
        userToken = newToken;
    }
    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private Button mLoginBtn, mSignUpBtn;
    private ProgressBar mProgressBar;
    private static String URL = "http://cs309-yt-2.misc.iastate.edu:8080";
    private View mProgressView;
    private View mLoginFormView;

    private ArrayList<UserSchema> mEntries = new ArrayList<>(); //users list
    private ArrayList<String> errors = new ArrayList<>(); //errors list
    private RequestQueue mQueue; //Volley Request Queue

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmailView = findViewById(R.id.email);
        mPasswordView = findViewById(R.id.password);
        mLoginBtn = findViewById(R.id.login_button);
        mSignUpBtn = findViewById(R.id.sign_up_button);
        mProgressBar = findViewById(R.id.login_progress);

        mQueue = Volley.newRequestQueue(this);

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });

        mSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

    }

    private void Login() {
        mProgressBar.setVisibility(View.VISIBLE);
        mLoginBtn.setVisibility(View.GONE);

        final String username = this.mEmailView.getText().toString().trim();
        final String password = this.mPasswordView.getText().toString().trim();

        /*
        //User Object
        JSONObject js = new JSONObject();
        try {
            js.put("username",username);
            js.put("password",password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        */

        final String verify = username + "," + password;
        StringRequest verifyUserInfo = new StringRequest(Request.Method.POST, URL + "/authentication",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("failed")) {
                            Toast.makeText(LoginActivity.this, "Authentication Failed.", Toast.LENGTH_SHORT).show();
                            mProgressBar.setVisibility(View.GONE);
                            mLoginBtn.setVisibility(View.VISIBLE);
                        } else {
                            userToken = response;
                            Log.i("USERTOKEN", userToken);
                            startActivity(new Intent(getBaseContext(), DashboardActivity.class)); //Go to dashboard
                            Toast.makeText(LoginActivity.this, "Welcome!",Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(LoginActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                        mProgressBar.setVisibility(View.GONE);
                        mLoginBtn.setVisibility(View.VISIBLE);
                    }
            }){
                @Override
                protected Map<String,String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("userpass", verify);
                    return params;
                }

            };
        mQueue.add(verifyUserInfo);
    }
}

