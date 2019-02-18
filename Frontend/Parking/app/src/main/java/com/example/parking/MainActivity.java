package com.example.parking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private  static final String REQUESTTAG = "String Request 1";
    private TextView mTextViewResult;
    private RequestQueue mQueue;
    private Button b0;
    private Button map;
    private Button Str;
    private Button Json;
    private Button login;
    private Button Image;
   private String test_url = "http://cs309-yt-2.misc.iastate.edu";
   private String json_test = "https://api.myjson.com/bins/x5bxa";
    //String test_url = "http://www.mocky.io/v2/5c64f4ef330000dd0fb999e7";
    private static final String TAG = MainActivity.class.getName();
    private RequestQueue mRequestQueue;
    private StringRequest stringRequest;
    private RequestQueue jRequestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        map = (Button) findViewById(R.id.button_map);
        map.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent Act_map = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(Act_map);
            }
        });

        Str = (Button) findViewById(R.id.Str);
        Str.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                sendRequestAndPrintResponse();

                //Intent Str = new Intent(MainActivity.this, Str.class);
                //startActivity(Str);
            }
        });

        mTextViewResult = findViewById(R.id.json_test);
        Button buttonParse = findViewById(R.id.json);

        mQueue = Volley.newRequestQueue(this);

        buttonParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonParse();
            }
        });



        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login_pg = new Intent(MainActivity.this, LoginPage.class);
                startActivity(login_pg);
            }
        });

    }

    public void sendRequestAndPrintResponse(){
        mRequestQueue = Volley.newRequestQueue(this);

        stringRequest = new StringRequest(Request.Method.GET, test_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "Success " + response.toString());


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "Error " + error.toString());

            }
        });

        stringRequest.setTag(REQUESTTAG);
        mRequestQueue.add(stringRequest);

       // mRequestQueue.cancelAll(REQUESTTAG);
    }

    public void jsonParse()
    {

        String url = "https://api.myjson.com/bins/x5bxa";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("employees");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject employee = jsonArray.getJSONObject(i);

                                String firstName = employee.getString("firstname");
                                int age = employee.getInt("age");
                                String mail = employee.getString("mail");

                                mTextViewResult.append(firstName + ", " + String.valueOf(age) + ", " + mail + "\n\n");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);
    }



    @Override
    protected void onStop(){
        super.onStop();
        if(mRequestQueue != null)
        {
            mRequestQueue.cancelAll(REQUESTTAG);
        }
    }

}





