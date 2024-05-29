package com.example.blufilm.Activity;

import android.content.Context;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.blufilm.Api.API;
import com.example.blufilm.DataBase.Database;
import com.example.blufilm.DataBase.FavDao;
import com.example.blufilm.DataBase.FavDatabase;
import com.example.blufilm.R;

public class favouriteActivity extends AppCompatActivity {
    private Context context;
    private RecyclerView rvFavourite;
    private FavDatabase databasefav;
    private API api;
    private FavDao favDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_favourite);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initView();





    }

    private void initView() {


        //Youtube
        //https://www.youtube.com/watch?v=rhtKP7Taxwo
    }


}