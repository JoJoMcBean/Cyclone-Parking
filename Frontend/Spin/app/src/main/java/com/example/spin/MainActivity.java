package com.example.spin;


import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import android.support.v4.app.Fragment;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

import static com.github.mikephil.charting.utils.ColorTemplate.JOYFUL_COLORS;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private String URL = "https://api.myjson.com/bins/1b5d2a";
    Spinner spinner;
    TextView  result, avaiablity;
    Button parse ;
    PieChart piechart;

    ParkingLot parkinglot1;
    ArrayList<PieEntry> yvalue ;
    private int available_spot, unavailable_spot;
    PieChartView pieChartView ;
    List<SliceValue> pieData ;
    RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pieChartView  = findViewById(R.id.chart);
        pieData = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this);
        avaiablity = (TextView) findViewById(R.id.availibty);
        avaiablity.setAlpha(0.0f);





        result = (TextView) findViewById(R.id.text_view_result);
        parse = (Button) findViewById(R.id.button_parse);
        parse .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JsonArrayParse();
                pieData.add(new SliceValue((float)unavailable_spot, Color.BLUE).setLabel("Unavailable Spots: " + Integer.toString(unavailable_spot)));
                pieData.add(new SliceValue((float) available_spot, Color.GRAY).setLabel("Available Spots: " +  Integer.toString(available_spot)));
                PieChartData pieChartData = new PieChartData(pieData);
                pieChartData.setHasLabels(true);
                pieChartView.setPieChartData(pieChartData);
                avaiablity.setAlpha(1.0f);
                avaiablity.setText("Total Available Spots: " + Integer.toString(parkinglot1.getTotalAvailableSpots())+"\n"
                        + "Total Unavailable Spots: " +  Integer.toString(parkinglot1.getTotalUnavailableSpots()));





            }
        });


        //assume spot 20 of lot1 is parked, send that request to server

        spinner = (Spinner) findViewById(R.id.spin_hommie);
     ArrayAdapter adapter =  ArrayAdapter.createFromResource(this, R.array.lot, android.R.layout.simple_spinner_item);
       spinner.setAdapter(adapter);
      spinner.setOnItemSelectedListener(this);

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //String text = parent.getItemAtPosition(position).toString();

        JsonArrayParse();
        Toast.makeText(this, "Available Spots: " + available_spot,Toast.LENGTH_LONG).show();




    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void JsonArrayParse(){


        String url  = "https://api.myjson.com/bins/1b5d2a";
        String url_lot2 = "https://api.myjson.com/bins/axzrm";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("lot1");
                    parkinglot1 = new ParkingLot(jsonArray.length());

                    for(int i=0; i<jsonArray.length(); i++)
                    {

                        JSONObject lot1 = jsonArray.getJSONObject(i);
                        int num_spot = lot1.getInt("SpotNum");
                        boolean status = lot1.getBoolean("Filled");
                        //result.append(String.valueOf(num_spot) + "      "  + Boolean.toString(status)+ "\n\n");  Parse the information
                        if(status == false)
                        {
                            parkinglot1.test_spotTaken(i);
                        }

                    }
                    available_spot = parkinglot1.getTotalAvailableSpots();
                    unavailable_spot = parkinglot1.getTotalUnavailableSpots();





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

        requestQueue.add(request);
        requestQueue.getCache().clear();



    }
}




