package com.example.cyparking;
//https://www.youtube.com/watch?v=y2xtLqP8dSQ

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ParseTest extends AppCompatActivity {

    private TextView mTextViewResult;
    private ArrayList<userSchema> mEntries = new ArrayList<>();
    private ArrayList<String> errors = new ArrayList<>();
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parse_test);

        mTextViewResult = findViewById(R.id.text_view_result);
        Button buttonParse = findViewById(R.id.button_parse);

        mQueue = Volley.newRequestQueue(this);
        buttonParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonParse();
            }
        });
    }

    private void jsonParse() {
        String url = "http://cs309-yt-2.misc.iastate.edu:8080/users";
        JsonArrayRequest request = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject user = response.getJSONObject(i);
                                String username = user.getString("username");
                                String password = user.getString("password");
                                String email = user.getString("email");
                                userSchema userData = new userSchema(username, password, email);
                                mEntries.add(userData);
                            } catch (JSONException e) {
                                errors.add("Error: " + e.getLocalizedMessage());
                            }
                        }
                        showUsers(mEntries);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(ParseTest.this, "Unable to fetch data: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        int numUsers = mEntries.size();
        Toast.makeText(ParseTest.this, "There are " + numUsers + "users", Toast.LENGTH_SHORT).show();
        mQueue.add(request);
    }

    private void showUsers(ArrayList<userSchema> users) {
        for (int i = 0; i < users.size(); i++) {
            mTextViewResult.append(users.get(i).getUsername() +  "\n\n");
        }
    }
}
