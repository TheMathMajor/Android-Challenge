package com.example.android.androidchallenge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {
    private RecyclerView recyclerView;
    private ArrayList<Guide> guides;
    private GuideAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        this.guides = new ArrayList<>();
        setup(); //initializes guides arraylist
    }

    public void setup() {
        // make a request => JSON string => parse => build guides arraylist
        StringRequest request = new StringRequest("https://guidebook.com/service/v2/upcomingGuides/", new Response.Listener<String>() {
            @Override
            public void onResponse(String string) {
                parseJsonData(string);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getApplicationContext(), "Some error occurred!!", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue rQueue = Volley.newRequestQueue(MainActivity.this);
        rQueue.add(request);
    }

    void parseJsonData(String jsonString) {
        try {
            JSONObject object = new JSONObject(jsonString);
            JSONArray guidesArray = object.getJSONArray("data");

            for(int i = 0; i < guidesArray.length(); ++i) {
                JSONObject guide = guidesArray.getJSONObject(i);
                String name = guide.getString("name");
                String location = "";
                if (guide.has("venue")){
                    JSONObject venue = guide.getJSONObject("venue");
                    if (venue.has("city")){
                        location += "\u2022 ";
                        location += venue.getString("city");
                    }
                    if (venue.has("state")){
                        location += ", ";
                        location += venue.getString("state");
                    }
                }
                String endDate = guide.getString("endDate");
                String iconURL = guide.getString("icon");
                this.guides.add(new Guide(name, location, endDate, iconURL)); //add event to list
            }

            adapter = new GuideAdapter(guides, getApplicationContext());
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
