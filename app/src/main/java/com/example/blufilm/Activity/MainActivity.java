package com.example.blufilm.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.blufilm.Adapter.FilmListAdapter;
import com.example.blufilm.Domain.Filmitem;
import com.example.blufilm.Domain.ListFilm;
import com.example.blufilm.R;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapterNewMovie,adapterUpComing;
    private RecyclerView recyclerViewNewMovies,recyclerViewUpComing;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest,mStringRequest2;
    private ProgressBar Loading1,Loading2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initview();
        sendRequest2();
        sendRequest1();

    }
    private void initview(){
        recyclerViewNewMovies=findViewById(R.id.view1);
        recyclerViewNewMovies.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerViewUpComing=findViewById(R.id.view2);
        recyclerViewUpComing.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        Loading1=findViewById(R.id.loading1);
        Loading2=findViewById(R.id.loading2);

    }
    private void sendRequest1(){
        mRequestQueue= Volley.newRequestQueue(this);
        Loading1.setVisibility(View.VISIBLE);
        mStringRequest=new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies?page=1", response -> {
            Gson gson=new Gson();
            Loading1.setVisibility(View.GONE);
            ListFilm items=gson.fromJson(response, ListFilm.class);
            adapterNewMovie=new FilmListAdapter(items);
            recyclerViewNewMovies.setAdapter(adapterNewMovie);
        }, error -> {
            Loading1.setVisibility(View.GONE);
        });
mRequestQueue.add(mStringRequest);
    }
    private void sendRequest2(){
        mRequestQueue= Volley.newRequestQueue(this);
        Loading2.setVisibility(View.VISIBLE);
        mStringRequest2=new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies?page=3", response -> {
            Gson gson=new Gson();
            Loading2.setVisibility(View.GONE);
            ListFilm items=gson.fromJson(response, ListFilm.class);
            adapterUpComing=new FilmListAdapter(items);
            recyclerViewUpComing.setAdapter(adapterUpComing);
        }, error -> {
            Loading2.setVisibility(View.GONE);
        });
mRequestQueue.add(mStringRequest2);
    }
}