package com.example.cyparking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A Register screen that offers register via username, password, email, and usertype.
 */
public class RegisterDefaultUserActivity extends AppCompatActivity {

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
    private EditText mNetIdView, mLicenseNumView, mCardNumView;
    private Button mRegisterBtn, mLoginBtn;
    private ProgressBar mProgressBar;
    private static String URL = "http://cs309-yt-2.misc.iastate.edu:8080";
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_default_user);

        mLicenseNumView = findViewById(R.id.license_num);
        mCardNumView = findViewById(R.id.card_num);
        mRegisterBtn = findViewById(R.id.register_button);
        mProgressBar = findViewById(R.id.register_progress);

        mRegisterBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v){
                Regist();
            }
        });

    }

    private void Regist() {
        mProgressBar.setVisibility(View.VISIBLE);
        mRegisterBtn.setVisibility(View.GONE);

        final String username = getIntent().getStringExtra("USER_USERNAME");
        final String netId = this.mNetIdView.getText().toString().trim();
        final String licenseNum = this.mLicenseNumView.getText().toString().trim();
        final String cardNum = this.mCardNumView.getText().toString().trim();

        //User Object
        JSONObject js = new JSONObject();
        try {
            js.put("username",username);
            js.put("net_id",netId);
            js.put("license",licenseNum);
            js.put("cardNum",cardNum);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //if (!js.isNull("user_type") && !js.isNull("username") && !js.isNull("email"))

        //Send JSON object to controller. Use response to verify success.
        JsonObjectRequest userRequest = new JsonObjectRequest(Request.Method.POST, URL + "/add/default", js,
                new Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Response", response.toString() + " this user");
                        Toast.makeText(RegisterDefaultUserActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        startActivity(new Intent(getBaseContext(), LoginActivity.class)); //Go to login
                    }
                },
                new ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegisterDefaultUserActivity.this, "Register Failed!", Toast.LENGTH_SHORT).show();
                        Log.d("DEFAULT ERROR RESPONSE", "" + error.toString());
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

