package com.example.blufilm.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.blufilm.Api.API;
import com.example.blufilm.DataBase.FavDao;
import com.example.blufilm.DataBase.FavDatabase;
import com.example.blufilm.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;

public class detailActivity extends AppCompatActivity {
ExtendedFloatingActionButton findMoreBtn;

private TextView titelTxt, movieRateTxt,genreTv,movieDateTxt,movieSummaryInfo,movieActorsInfo,movieDirectorsInfo;
private FavDatabase myFavDatabase;
private FavDao favDao;
private String movieName,cover,desc,url;
private Double rate;
private Integer date;
private String actors;
private String directors,genres;
private ShapeableImageView pic1;
private ImageView backImg,pic2;
private FavDao myfavDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initView();
        dataBase();

        cover=getIntent().getStringExtra("cover").toString();

        Glide.with(this).load(cover).into(pic1);
        Glide.with(this).load(cover).into(pic2);


        movieName =getIntent().getStringExtra("name").toString();
         titelTxt.setText(movieName);

        rate =getIntent().getDoubleExtra("rate",0.0);
        movieRateTxt.setText(Double.toString(rate));

        date=getIntent().getIntExtra("date",0);
        movieDateTxt.setText(Integer.toString(date));

        desc=getIntent().getStringExtra("desc").toString();
        movieSummaryInfo.setText(desc);

        actors= (getIntent().getStringExtra("actors"));
        String dataWithoutBrackets2 = actors.substring(2, actors.length() - 2);
        String[] dataArray2 = dataWithoutBrackets2.split(",");
        for (int i = 0; i < dataArray2.length; i++) {
            dataArray2[i] = dataArray2[i].replaceAll("\"", "").trim();
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        for (String data : dataArray2) {
            stringBuilder2.append(data).append(",");
        }
        movieActorsInfo.setText(stringBuilder2.toString());

        directors= (getIntent().getStringExtra("directors"));
        String dataWithoutBrackets1 = directors.substring(2, directors.length() - 2);
        String[] dataArray1 = dataWithoutBrackets1.split(",");
        for (int i = 0; i < dataArray1.length; i++) {
            dataArray1[i] = dataArray1[i].replaceAll("\"", "").trim();
        }
        StringBuilder stringBuilder1 = new StringBuilder();
        for (String data : dataArray1) {
            stringBuilder1.append(data).append(",");
        }
        movieDirectorsInfo.setText(stringBuilder1.toString());

        genres= (getIntent().getStringExtra("genre"));
        String dataWithoutBrackets = genres.substring(2, genres.length() - 2);
        String[] dataArray = dataWithoutBrackets.split(",");
        for (int i = 0; i < dataArray.length; i++) {
            dataArray[i] = dataArray[i].replaceAll("\"", "").trim();
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String data : dataArray) {
            stringBuilder.append(data).append(",");
        }
        genreTv.setText(stringBuilder.toString());

        url=getIntent().getStringExtra("url");
        findMoreBtn.setOnClickListener(v -> {

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.imdb.com/"+url));
            startActivity(intent);

        });


        Log.d("TAG", "onCreate: di"+directors);

    }

    private void dataBase() {
        myFavDatabase= Room.databaseBuilder(this,FavDatabase.class,"favMovie")
                .allowMainThreadQueries().fallbackToDestructiveMigration().build();
        myfavDao=myFavDatabase.getFavDao();
    }

    private void initView(){
        titelTxt=findViewById(R.id.movieNameTv);
        pic1=findViewById(R.id.posterNormalImg);
        pic2=findViewById(R.id.posterBigImg);
        movieRateTxt=findViewById(R.id.movieRateTv);
        genreTv=findViewById(R.id.genrsTv);
        movieDateTxt=findViewById(R.id.movieDateTv);
        movieSummaryInfo=findViewById(R.id.movieSummery);
        movieActorsInfo=findViewById(R.id.movieActorInfo);
        backImg=findViewById(R.id.backImg);
        movieDirectorsInfo=findViewById(R.id.movieDirectorInfo);
        findMoreBtn=findViewById(R.id.findMoreBtn);
        backImg.setOnClickListener(v -> finish());
    }

}