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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.UUID;

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
 * A Register screen that offers register via username, password, email, and usertype.
 */
public class RegisterActivity extends AppCompatActivity {

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
    private EditText mUsernameView, mPasswordView, mUsertypeView;
    private Button mRegisterBtn, mLoginBtn;
    private ProgressBar mProgressBar;
    private static String URL_REGIST = "http://cs309-yt-2.misc.iastate.edu:8080";
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mEmailView = findViewById(R.id.email);
        mUsernameView = findViewById(R.id.username);
        mPasswordView = findViewById(R.id.password);
        mUsertypeView = findViewById(R.id.usertype);
        mRegisterBtn = findViewById(R.id.register_button);
        mLoginBtn = findViewById(R.id.login_button);
        mProgressBar = findViewById(R.id.register_progress);

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Regist();
            }
        });

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

    }

    private void Regist() {
        mProgressBar.setVisibility(View.VISIBLE);
        mRegisterBtn.setVisibility(View.GONE);

        final String userType = this.mUsertypeView.getText().toString().trim();
        final String email = this.mEmailView.getText().toString().trim();
        final String username = this.mUsernameView.getText().toString().trim();
        final String password = this.mPasswordView.getText().toString().trim();
        //UUID.randomUUID()
        //User Object
        JSONObject js = new JSONObject();
        try {
            js.put("user_type",userType);
            js.put("username",username);
            js.put("email",email);
            js.put("password",password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //if (!js.isNull("user_type") && !js.isNull("username") && !js.isNull("email"))

        //Send JSON object to controller. Use response to verify success.
        JsonObjectRequest userRequest = new JsonObjectRequest(Request.Method.POST, URL_REGIST + "/add/user", js,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Response", response.toString() + " this user");
                        Toast.makeText(RegisterActivity.this, "Register Success!", Toast.LENGTH_SHORT).show();
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class)); //Go to login
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegisterActivity.this, "Register Failed!", Toast.LENGTH_SHORT).show();
                        Log.d("ON ERROR RESPONSE", "" + error.toString());
                        mProgressBar.setVisibility(View.GONE);
                        mRegisterBtn.setVisibility(View.VISIBLE);
                    }
                });
/*        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
            Map<String,String> params = new HashMap<>();
            params.put("user_type",userType);
            params.put("username",username);
            params.put("email",email);
            params.put("password",password);
            return params;
        }*/
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.getCache().clear();
        requestQueue.add(userRequest);
    }
}

