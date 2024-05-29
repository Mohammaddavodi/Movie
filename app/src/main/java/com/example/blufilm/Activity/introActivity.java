package com.example.blufilm.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;

import com.example.blufilm.R;
import com.example.blufilm.authenticUser.SinginActivity;
import com.example.blufilm.authenticUser.loginActivity;

public class introActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        new Handler().postDelayed(() -> {

            Intent mainIntent = new Intent(introActivity.this,loginActivity.class);
            introActivity.this.startActivity(mainIntent);
        }, 2500);


    }
}