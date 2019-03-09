package com.example.cyparking;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.cyparking.parkinglog.ParkingLog;
import com.example.cyparking.parkinglog.ParkingLogAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {
    private static String URL = "http://cs309-yt-2.misc.iastate.edu:8080";

    private ProgressBar mProgressBar;

    private RequestQueue mQueue; //Volley Request Queue

    private TextView mThisNameView, mThisUsernameView, mThisEmailView;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter parkingLogsAdapter;
    ArrayList<ParkingLog> logs = new ArrayList<>();

    UserSchema userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mProgressBar = findViewById(R.id.load_progress);

        //mThisEmailView = findViewById(R.id.this_email);
        //mThisUsernameView = findViewById(R.id.this_username);

        //Load User Data
        mProgressBar.setVisibility(View.VISIBLE);
        StringRequest getUser = new StringRequest(Request.Method.POST, URL + "/get/default",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("THIS USER", response);
                        String[] userInfo = response.split(",");
                        userData = new DefaultUserSchema(userInfo[0], userInfo[1], userInfo[2], userInfo[3], userInfo[4], userInfo[5]);
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
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("token", LoginActivity.getToken());
                return params;
            }

        };
        mQueue.add(getUser);

        //mThisEmailView.setText(userData.getEmail());
        //mThisUsernameView.setText(userData.getUsername());


        //Recycle View => "Recent Parking History"
        recyclerView = (RecyclerView) findViewById(R.id.parking_log);

        //Dummy filler
        logs.add(new ParkingLog("Spot 4E", "Hilton Coliseum"));
        logs.add(new ParkingLog("Spot 4E", "Hilton Coliseum"));
        logs.add(new ParkingLog("Spot 4E", "Hilton Coliseum"));
        logs.add(new ParkingLog("Spot 4E", "Hilton Coliseum"));
        logs.add(new ParkingLog("Spot 4E", "Hilton Coliseum"));

        parkingLogsAdapter = new ParkingLogAdapter(logs);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(parkingLogsAdapter);
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


