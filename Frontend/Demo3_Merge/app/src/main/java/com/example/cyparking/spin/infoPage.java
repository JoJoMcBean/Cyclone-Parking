package com.example.cyparking.spin;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cyparking.LoginActivity;
import com.example.cyparking.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public class infoPage extends AppCompatActivity {


    private Button Bstart, Bstop, Breset;
    private TextView textView;
    private Chronometer chronometer;
    private long lastPause;
    private String url = "http://cs309-yt-2.misc.iastate.edu:8080/leaveSpot";
    private String url_getParkedInfo = "http://cs309-yt-2.misc.iastate.edu:8080/getParkedInfo";
    private String parkedInfo;
    private int rate = 1;
    private long price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_page);

        Bstart = (Button) findViewById(R.id.start);
        Bstop = (Button) findViewById(R.id.stop);
        Breset = (Button) findViewById(R.id.reset);

        textView = (TextView) findViewById(R.id.info);
        getInfo();


        chronometer = (Chronometer) findViewById(R.id.timer);


        Bstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lastPause != 0) {
                    chronometer.setBase(chronometer.getBase() + SystemClock.elapsedRealtime() - lastPause);
                } else {
                    chronometer.setBase(SystemClock.elapsedRealtime());
                }

                chronometer.start();
                Bstart.setEnabled(false);
                Bstop.setEnabled(true);
            }
        });

        Bstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastPause = SystemClock.elapsedRealtime();
                chronometer.stop();
                price = lastPause * rate / 1000;
                Toast.makeText(infoPage.this, "Your rate is:" + price, Toast.LENGTH_SHORT).show();
                Bstop.setEnabled(false);
                Bstart.setEnabled(true);
            }
        });


        Breset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StringRequest_leaveSpot();

                finish();





            }
        });


    }


    public void StringRequest_leaveSpot() {


        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parms = new HashMap<String, String>();
                String result = "";
                result += LoginActivity.getToken();
                result += ",";
                result += price;

                parms.put("tokenpaid", result);
                return parms;
            }
        };

        requestQueue.add(stringRequest);


    }


    public void getInfo() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_getParkedInfo, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String[] values = response.split(",");
                String username = values[0];
                String lot = values[1];
                String spot = values[2];
                String liscence_plate = values[3];
                String epoch_time = values[4];
                long time = Long.parseLong(epoch_time );
                Date date = new Date(time);
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
                format.setTimeZone(TimeZone.getTimeZone("GMT-6"));
                String formatted = format.format(date);





                parkedInfo = "Your Car (" + liscence_plate + ") is parked at Lot: " + lot + ", spot "+spot + "from: " + formatted;

                textView.setText(parkedInfo);
                Log.d("response", "onResponse: " + parkedInfo);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parms = new HashMap<String, String>();
                parms.put("token", LoginActivity.getToken());
                Log.d("token_test", LoginActivity.getToken() + "fwgfgfgf");

                return parms;
            }
        };

        requestQueue.add(stringRequest);



    }







}
