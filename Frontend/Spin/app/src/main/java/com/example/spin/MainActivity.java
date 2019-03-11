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
    Spinner spinner;
    TextView  result, avaiablity;
    Button parse, nextpage ;
    PieChart piechart;

    ParkingLot parkinglot1;
    ArrayList<PieEntry> yvalue ;
    private int available_spot, unavailable_spot;
    PieChartView pieChartView ;
    List<SliceValue> pieData ;
    RequestQueue requestQueue;
    String path;
    String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pieChartView  = findViewById(R.id.chart);
        pieData = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this);
        avaiablity = (TextView) findViewById(R.id.availibty);
        avaiablity.setAlpha(0.0f);
        nextpage = (Button) findViewById(R.id.button2);
        url = "http://cs309-yt-2.misc.iastate.edu:8080/";


        nextpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ava, unava;
                boolean[] spots_status = new boolean[parkinglot1.getSpots_quantity()];
                for(int i=0; i<spots_status.length; i++)
                {
                    spots_status[i] = parkinglot1.getNumStatus(i);
                }

                ava = Integer.toString(available_spot);
                unava = Integer.toString(unavailable_spot);
                Intent newpage = new Intent(getApplicationContext(),lot_Page.class);
                newpage.putExtra("Available",ava);
                newpage.putExtra("Unavailable",unava);
                newpage.putExtra("Spot_Array",spots_status);
                newpage.putExtra("Path",path);


               // Toast.makeText(getApplicationContext(), Integer.toString(available_spot), Toast.LENGTH_SHORT).show();
                startActivity(newpage);


            }
        });





        result = (TextView) findViewById(R.id.text_view_result);
        parse = (Button) findViewById(R.id.button_parse);
        parse .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JsonArrayParse();






            }
        });



        spinner = (Spinner) findViewById(R.id.spin_hommie);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.lot, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //String text = parent.getItemAtPosition(position).toString();

        path = parent.getItemAtPosition(position).toString();
        Toast.makeText(this, parent.getItemAtPosition(position).toString(),Toast.LENGTH_LONG).show();




    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void JsonArrayParse(){



        JsonArrayRequest request = new JsonArrayRequest(url + path, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    Log.d("xxx", "onResponse: spot added");
                    parkinglot1 = new ParkingLot(response.length());

                    for(int i=0; i<response.length(); i++)
                    {

                        JSONObject lot1 = response.getJSONObject(i);
                        int num_spot = lot1.getInt("spotNum");
                        boolean status = lot1.getBoolean("isFilled");
                        //result.append(String.valueOf(num_spot) + "      "  + Boolean.toString(status)+ "\n\n");  Parse the information
                        if(status == false)
                        {
                            parkinglot1.test_spotTaken(i);
                        }

                    }
                    available_spot = parkinglot1.getTotalAvailableSpots();
                    unavailable_spot = parkinglot1.getTotalUnavailableSpots();
                    pieData.add(new SliceValue((float)unavailable_spot, Color.BLUE).setLabel("Unavailable Spots: " + Integer.toString(unavailable_spot)));
                    pieData.add(new SliceValue((float) available_spot, Color.GRAY).setLabel("Available Spots: " +  Integer.toString(available_spot)));
                    PieChartData pieChartData = new PieChartData(pieData);
                    pieChartData.setHasLabels(true);
                    pieChartView.setPieChartData(pieChartData);
                    avaiablity.setAlpha(1.0f);
                    avaiablity.setText("Total Available Spots: " + Integer.toString(parkinglot1.getTotalAvailableSpots())+"\n"
                            + "Total Unavailable Spots: " +  Integer.toString(parkinglot1.getTotalUnavailableSpots()));


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




