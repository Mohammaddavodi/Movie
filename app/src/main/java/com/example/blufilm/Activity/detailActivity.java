package com.example.blufilm.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.example.blufilm.R;
import com.google.android.material.imageview.ShapeableImageView;

public class detailActivity extends AppCompatActivity {
private RequestQueue requestQueue;
private StringRequest stringRequest;
private ProgressBar progressBar;
private TextView titelTxt, movieRateTxt, movieTimeTxt,movieDateTxt,movieSummaryInfo,movieActorsInfo;
private NestedScrollView scrollView;
private int idFilm;
private ShapeableImageView pic1;
private ImageView backImg,pic2;
private RecyclerView.Adapter adapterImgList;
private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        idFilm=getIntent().getIntExtra("id",0);
        initView();

    }
    private void initView(){
        titelTxt=findViewById(R.id.movieNameTv);
        progressBar=findViewById(R.id.detailsLoading);
        scrollView=findViewById(R.id.scrollView3);
        pic1=findViewById(R.id.posterNormalImg);
        pic2=findViewById(R.id.posterBigImg);
        movieRateTxt=findViewById(R.id.movieRateTv);
        movieTimeTxt=findViewById(R.id.movieTimeTv);
        movieDateTxt=findViewById(R.id.movieDateTv);
        movieSummaryInfo=findViewById(R.id.movieSummery);
        movieActorsInfo=findViewById(R.id.movieActorInfo);
        backImg=findViewById(R.id.backImg);
        recyclerView=findViewById(R.id.imagesRv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        backImg.setOnClickListener(v -> finish());


    }
}