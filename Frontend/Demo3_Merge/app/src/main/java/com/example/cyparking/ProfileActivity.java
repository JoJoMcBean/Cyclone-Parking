package com.example.cyparking;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import android.app.AlertDialog;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cyparking.parkinglog.ParkingLog;
import com.example.cyparking.parkinglog.ParkingLogAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {
    private static String URL = "http://cs309-yt-2.misc.iastate.edu:8080";

    final Context context = this;

    private EditText result;

    private ProgressBar mProgressBar;

    private RequestQueue mQueue;

    private TextView mThisUsernameView, mThisEmailView, mThisLicenseNum, mThisCreditNum;

    private Button mApplyChanges;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter parkingLogsAdapter;
    ArrayList<ParkingLog> logs = new ArrayList<>();

    DefaultUserSchema userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mQueue = Volley.newRequestQueue(this);

        mProgressBar = findViewById(R.id.load_progress);

        mApplyChanges = findViewById(R.id.apply_changes_btn);
        mApplyChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUserInfo();
            }
        });

        //Update local user obj
        mThisEmailView = findViewById(R.id.this_email);
        mThisEmailView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        userData.setEmail(s.toString());

            }
            @Override
            public void afterTextChanged(Editable s) { }
        });

        mThisUsernameView = findViewById(R.id.this_username);

        mThisLicenseNum = findViewById(R.id.this_license_num);
        mThisLicenseNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                userData.setLicenseNum(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mThisCreditNum = findViewById(R.id.this_credit_num);
        mThisCreditNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                userData.setCreditCardNum(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //Load User Data
        mProgressBar.setVisibility(View.VISIBLE);
        StringRequest getUser = new StringRequest(Request.Method.POST, URL + "/get/default",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("THIS USER", response);
                        String[] userInfo = response.split(",");

                        userData = new DefaultUserSchema(userInfo[0], userInfo[1], userInfo[2], userInfo[3], userInfo[4], userInfo[5]);
                        mThisEmailView.setText(userData.getEmail());
                        mThisUsernameView.setText(userData.getUsername());
                        mThisLicenseNum.setText(userData.getLicenseNum());
                        mThisCreditNum.setText(userData.getCreditCardNum());

                        mProgressBar.setVisibility(View.GONE);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(getBaseContext(), "Unable to fetch data: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("token", LoginActivity.getToken());
                return params;
            }
        };
        mQueue.add(getUser);

        //Load User Logs
        JSONArray jsArr = new JSONArray();
        JSONObject js = new JSONObject();
        try {
            js.put("token",LoginActivity.getToken());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        jsArr.put(js);
        mProgressBar.setVisibility(View.VISIBLE);
        JsonArrayRequest getLogs = new JsonArrayRequest(Request.Method.POST, URL + "/history", jsArr,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Process the JSON
                        try{
                            // Loop through the array elements
                            Log.d("responser", response + " poopy");
                            for(int i=0;i<response.length();i++){
                                // Get current json object
                                JSONObject jsLog = response.getJSONObject(i);
                                // Get the current student (json object) data
                                String spaceID = jsLog.getString("spotnum");
                                String username = jsLog.getString("username");
                                String lotID = jsLog.getString("lotid");
                                String timeStart = jsLog.getString("timestart");
                                String timeEnd = jsLog.getString("timeend");
                                String paid = jsLog.getString("paid");
                                ParkingLog log = new ParkingLog(spaceID, lotID, username, timeStart, timeEnd, paid);
                                logs.add(log);
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                        //Recycle View => "Recent Parking History"
                        recyclerView = (RecyclerView) findViewById(R.id.parking_log);
                        parkingLogsAdapter = new ParkingLogAdapter(logs);
                        recyclerView.setLayoutManager(new LinearLayoutManager(com.example.cyparking.ProfileActivity.this));
                        recyclerView.setAdapter(parkingLogsAdapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(getBaseContext(), "Unable to fetch data: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        mQueue.add(getLogs);
    }

    //Edit TextViews Dialog
    public void onClick(View v) {
        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.edit_text_prompt, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);
        final EditText userInput = (EditText) promptsView.findViewById(R.id.editTextDialogUserInput);
        // set dialog message
        final TextView thisView = (TextView) v;
        alertDialogBuilder.setCancelable(false).setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // get user input and set it to result
                        // edit text
                        thisView.setText(userInput.getText());
                    }
                }).setNegativeButton("Cancel",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            dialog.cancel();
                        }
                });
        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();
    }

    private void updateUserInfo() {
        final String userInfo = userData.getPassword() + "," + userData.getEmail() + "," + userData.getLicenseNum()
                + "," + userData.getCreditCardNum() + "," + LoginActivity.getToken();
        StringRequest updateUser = new StringRequest(Request.Method.POST, URL + "/update/userinfo",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("success")) {
                            Log.d("UPDATE STATUS", response);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(getBaseContext(), "Unable to fetch data: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("userInfo", userInfo);
                return params;
            }
        };
        mQueue.add(updateUser);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



}


