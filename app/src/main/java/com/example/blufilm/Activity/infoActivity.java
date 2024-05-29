package com.example.blufilm.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.blufilm.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
public class infoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_info);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        intent();
    }
    private void intent() {
        ExtendedFloatingActionButton contractBtn=(ExtendedFloatingActionButton) findViewById(R.id.closeBtnDetails);
        contractBtn.setOnClickListener(v -> {
                     Intent intent = new Intent(Intent.ACTION_SEND)
                            .setType("text/plain")
                                    .putExtra(Intent.EXTRA_SUBJECT,"contract for details")
                                            .putExtra(Intent.EXTRA_TEXT,"");
                    startActivity(intent);
//                    AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
//                    builder.setTitle("Choose an animal");
//                    String[] animals = {"horse", "cow", "camel", "sheep", "goat"};
//                    builder.setItems(animals, new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            switch (which) {
//                                case 0: // horse
//                                case 1: // cow
//                                case 2: // camel
//                                case 3: // sheep
//                                case 4: // goat
//                            }
//                        }
//
//                    });
        }
        );
        ImageView backImg=(ImageView)findViewById(R.id.backImg);
        backImg.setOnClickListener(v -> finish());
    }
}