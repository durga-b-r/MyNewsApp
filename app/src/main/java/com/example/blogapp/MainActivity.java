package com.example.blogapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String API_KEY ="20f372550dc146828ccc9dfed60cedfe";
    String API_URL ="https://newsapi.org/v2/top-headlines?country=in&apiKey=" +API_KEY;
    RecyclerView newsRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newsRecycler = findViewById(R.id.recyclerNews);

        final ProgressDialog loader = new ProgressDialog(this);
        loader.setMessage("Loading data....");
        loader.show();
        loader.setCancelable(false);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,API_URL,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.e("RESPONSE",response.toString());
                loader.dismiss();
                handleResponse(response);
            }
        },new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_SHORT).show();
                loader.dismiss();
                Log.e("ERROR",error.toString());
            }
        });
       MySingleton.getInstance(getApplicationContext()).addToRequestQueue(request);
    }

    private void handleResponse(JSONObject response) {


        try {
            JSONArray articlesArray = response.getJSONArray("articles");
            ArrayList<ModelNews> newsArray = new ArrayList<>();
            for (int i = 0; i < articlesArray.length(); i++) {
                JSONObject newsObject = (JSONObject) articlesArray.get(i);
                String newsTitle = newsObject.getString("title");
                String  newsDsc =  newsObject.getString("description");
                String newsUrl =   newsObject.getString("url");
                String newsImageurl =newsObject.getString("urlToImage");

                ModelNews newsItem = new ModelNews(newsTitle,newsDsc,newsUrl,newsImageurl);

                newsArray.add(newsItem);

            }
            newsRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false));
            newsRecycler.setAdapter(new Adapternews(getApplicationContext(),newsArray));

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(MainActivity.this,"Inside Exception Block",Toast.LENGTH_SHORT).show();
        }

    }
}