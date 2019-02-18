package com.example.testsignup;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {
    private Button signup;
    private EditText Username, Password, Usertype, Email;
    private String url = "http://cs309-yt-2.misc.iastate.edu:8080/add/user/";
    //private String url = "http://cs309-yt-2.misc.iastate.edu/users";
   // private String url = "https://89055888-01f2-4079-bfb7-b8ac9f1c98b2.mock.pstmn.io";
    AlertDialog.Builder builder;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signup = (Button) findViewById(R.id.button_login);
        Username = (EditText) findViewById(R.id.editText_username);
        Password = (EditText) findViewById(R.id.editText_password);
        Usertype = (EditText) findViewById(R.id.editText_usertype);
        Email = (EditText) findViewById(R.id.editText_email);
        builder = new AlertDialog.Builder(MainActivity.this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email, password, username, usertype;
                email = Email.getText().toString();
                password = Password.getText().toString();
                username = Username.getText().toString();
                usertype = Usertype.getText().toString();

                JSONObject JS = new JSONObject();
                try{
                    JS.put("username",username);
                    JS.put("password",password);
                    JS.put("user_type",usertype);
                    JS.put("email", email);

                }catch(JSONException e){
                    e.printStackTrace();
                }

                JsonRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, JS, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        builder.setTitle("Server Response");
                        builder.setMessage("Response : "+ response);
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Email.setText("");
                                Password.setText("");
                                Username.setText("");
                                Usertype.setText("");

                            }
                        }


                },


                    }
                };
                J

            }
        });
    }
}
