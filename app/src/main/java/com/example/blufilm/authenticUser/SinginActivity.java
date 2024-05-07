package com.example.blufilm.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.blufilm.DataBase.Database;
import com.example.blufilm.DataBase.UserDao;
import com.example.blufilm.DataBase.UserTable;
import com.example.blufilm.R;

public class SinginActivity extends AppCompatActivity {
    private Database myDb;
    private UserDao userDao;
    private EditText userEdt,passEdt;
    private Button loginBtn;
    public static boolean isAllowed=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myDb= Room.databaseBuilder(this, Database.class,"userTable")
                .allowMainThreadQueries().fallbackToDestructiveMigration().build();
        userDao=myDb.getDao();

    }

    private void initView() {
        userEdt=findViewById(R.id.edtUserName);
        passEdt=findViewById(R.id.edtPassword);
        loginBtn=findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(v -> {
         if (userEdt.getText().toString().isEmpty() || passEdt.getText().toString().isEmpty()){
             Toast.makeText(this, "please Fill User and Password", Toast.LENGTH_SHORT).show();
         }else if(userEdt.getText().toString().equals("test") &&
                 passEdt.getText().toString().equals("test")) {
         startActivity(new Intent(SinginActivity.this,MainActivity.class));
         }
        });

    }

    private void initLogin(){
        userEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String userName=s.toString();
                if (userDao.is_taken(userName)){
                    isAllowed=false;
                    Toast.makeText(SinginActivity.this, "Already Taken", Toast.LENGTH_SHORT).show();
                }else {
                    isAllowed=true;
                }
            }
        }
        );
        loginBtn.setOnClickListener(v -> {
            if(isAllowed){
                UserTable userTable=new UserTable(0,userEdt.getText().toString(),
                        passEdt.getText().toString());
                userDao.insertUser(userTable);
            }else {
                Toast.makeText(this, "UserName Already Taken", Toast.LENGTH_SHORT).show();
            }
        });


    }
}