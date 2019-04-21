package com.example.cyparking.spin;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cyparking.LoginActivity;
import com.example.cyparking.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class lot_Page extends AppCompatActivity {


    String url = "http://cs309-yt-2.misc.iastate.edu:8080/takeSpot";
    GridView gridView;
    private String available_spot;
    private String unavailable_spot;
    private String path;
    private String lot;
    private boolean[] Spot_Status;
    static final String[] numbers = new String[]{
            "0", "1", "2", "3", "4",
            "5", "6", "7", "8", "9"};
    int available_color = Color.GREEN;
    int unavailable_color = Color.RED;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lot_page);

        gridView = (GridView) findViewById(R.id.gridView1);
        Intent intent = getIntent();
        available_spot = intent.getStringExtra("Available");
        unavailable_spot = intent.getStringExtra("Unavailable");
        Spot_Status = new boolean[intent.getBooleanArrayExtra("Spot_Array").length];
        Spot_Status = intent.getBooleanArrayExtra("Spot_Array");
        path = intent.getStringExtra("Path");
        lot = intent.getStringExtra("Lot");
        Toast.makeText(this, lot, Toast.LENGTH_SHORT).show();

        final List<String> test = new ArrayList<String>(Arrays.asList(numbers));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, numbers);

        ArrayAdapter<String> aaa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, numbers){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                View view = super.getView(position, convertView, parent);

                if(Spot_Status[position] == false)
                {
                    view.setBackgroundColor(unavailable_color); // available -> green
                }

                else
                {
                    view.setBackgroundColor(available_color);   // unavailable ->red
                }

                return view;
            }
        };

        gridView.setAdapter(aaa);


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                //Toast.makeText(getApplicationContext(),
                //((TextView) v).getText(), Toast.LENGTH_SHORT).show();
                //Toast.makeText(getApplicationContext(), available_spot, Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), Boolean.toString(Spot_Status[position]), Toast.LENGTH_SHORT).show();


                if (Spot_Status[position] == false) {

                    dialog_spotTaken();
                    // Request_takeSpot(position);

                } else if (Spot_Status[position] == true) {
                    dialog_spotFree(position);
//                    try {
//                        Request_takeSpotJson(position);
//                       // StringRequest_takeSpot(position);
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }

                    StringRequest_takeSpot(position);

//                    Request_takeSpot(position);
                }




            }
        });

    }

    public void dialog_spotTaken() {
        new AlertDialog.Builder(lot_Page.this)
                .setTitle("Spot is taken!")
                .setMessage("Sorry, The Spot has been taken, Please select another")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }
                }).show();
    }


    public void dialog_spotFree(final int num) {
        new AlertDialog.Builder(lot_Page.this)
                .setTitle("Selected Spot is Available")
                .setMessage("Do you want to lock this spot?")

                .setPositiveButton("YES",
                        new DialogInterface.OnClickListener() {
                            @TargetApi(11)
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(lot_Page.this, "Spot Locked!", Toast.LENGTH_LONG).show();
                                //Request_takeSpot(num);


                                gridView.getChildAt(num).setBackgroundColor(Color.RED);
                                Intent next = new Intent(getApplicationContext(), infoPage.class);
                                startActivity(next);
                                dialog.cancel();


                            }
                        })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @TargetApi(11)
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(lot_Page.this, "Damn", Toast.LENGTH_LONG).show();
                        dialog.cancel();
                    }
                }).show();

    }



    public void Request_takeSpotJson(final int num) throws JSONException {
        JSONObject data = new JSONObject();
        data.put("lot",lot);
        data.put("spot",num);
        data.put("token", LoginActivity.getToken());
        Log.d("why null", lot + " " + num);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, data, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("rua",response.toString());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

            }
        });

        requestQueue.add(jsonObjectRequest);
    }



    public void StringRequest_takeSpot(final int num) {


        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(lot_Page.this, response, Toast.LENGTH_LONG).show();
                Log.d("xxx", "onResponse: " + response);
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
                result = lot + "," + num + "," + LoginActivity.getToken();
                Log.d("test", result);
                parms.put("lotspottoken",result);

                return parms;
            }
        };

        requestQueue.add(stringRequest);




    }

    public static int getPixelsFromDPs(Activity activity, int dps) {
        Resources r = activity.getResources();
        int px = (int) (TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dps, r.getDisplayMetrics()));
        return px;

    }


    public  int total_spot(String a, String b)
    {
        return 10;
    }

    public String test_info(String a, String b)
    {
        return "mr.poop,A,9";
    }
    public String take_spot(String a, String b)
    {
        return "Spot Added";
    }
}

