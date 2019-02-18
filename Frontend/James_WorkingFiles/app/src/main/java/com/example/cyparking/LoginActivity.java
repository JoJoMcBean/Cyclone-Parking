package com.example.cyparking;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

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
    private Button mLoginBtn;
    private ProgressBar mProgressBar;
    private static String URL = "http://cs309-yt-2.misc.iastate.edu/";
    private View mProgressView;
    private View mLoginFormView;

    private ArrayList<userSchema> mEntries = new ArrayList<>(); //users list
    private ArrayList<String> errors = new ArrayList<>(); //errors list
    private RequestQueue mQueue; //Volley Request Queue

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmailView = findViewById(R.id.email);
        mPasswordView = findViewById(R.id.password);
        mLoginBtn = findViewById(R.id.login_button);
        mProgressBar = findViewById(R.id.register_progress);

        mQueue = Volley.newRequestQueue(this);

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Login();
            }
        });

    }

    private void Login() {
        mProgressBar.setVisibility(View.VISIBLE);
        mLoginBtn.setVisibility(View.GONE);

        final String email = this.mEmailView.getText().toString().trim();
        final String password = this.mPasswordView.getText().toString().trim();

        //Get ALL users and put into "mEntries"
        JsonArrayRequest usersRequest = new JsonArrayRequest(URL + "/users",
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject user = response.getJSONObject(i);
                                String username = user.getString("username");
                                String password = user.getString("password");
                                String userType = user.getString("user_type");
                                String email = user.getString("email");
                                userSchema userData = new userSchema(userType, username, password, email);
                                mEntries.add(userData);
                            } catch (JSONException e) {
                                errors.add("Error: " + e.getLocalizedMessage());
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(LoginActivity.this, "Unable to fetch data: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        Log.d("Num Users: ", Integer.toString(mEntries.size()));
        mQueue.add(usersRequest);
        loginVerify(email, password);
    }

    //Temporary Login Verification
    public void loginVerify(String email, String password) {
        for (int i = 0; i < mEntries.size(); i++) {
            if (mEntries.get(i).getEmail().equals(email) && mEntries.get(i).getPassword().equals(password)) {
                startActivity(new Intent(LoginActivity.this, Dashboard.class)); //Go to dashboard
            }
        }
    }
}

