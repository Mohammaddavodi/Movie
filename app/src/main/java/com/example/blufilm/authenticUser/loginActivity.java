package com.example.blufilm.authenticUser;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.blufilm.Activity.MainActivity;
import com.example.blufilm.DataBase.Database;
import com.example.blufilm.DataBase.UserDao;
import com.example.blufilm.R;

public class loginActivity extends AppCompatActivity {
private Database db;
private UserDao userDao;
private EditText edtUserLogin,edtPassLogin;
private TextView makeAccount;
private Button loginBtn;

private UserManager userManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        SharedPreferences sharedPreferences =getSharedPreferences("validate", MODE_PRIVATE);
        boolean data = sharedPreferences.getBoolean("data", false);

        if (data == true){
            startActivity(new Intent(loginActivity.this, MainActivity.class));
        }
        initView();


        userManager=new UserManager(this);

        loginBtn.setOnClickListener(v -> {

            String userName=edtUserLogin.getText().toString();
            String password=edtPassLogin.getText().toString();


                if (userDao.login(userName,password)){
                    startActivity(new Intent(loginActivity.this, MainActivity.class));
                     SharedPreferences validate;
                    validate=getApplicationContext().getSharedPreferences("validate",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor= validate.edit();
                    editor.putBoolean("data",true);
                    editor.apply();
                }else {
                    SharedPreferences validate;
                    validate=getApplicationContext().getSharedPreferences("validate",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor= validate.edit();
                    editor.putBoolean("data",false);
                    editor.apply();
                    Toast.makeText(this, "Inavalid UserName or Password", Toast.LENGTH_SHORT).show();
                }




        });
        db= Room.databaseBuilder(this, Database.class,"userTable")
                .allowMainThreadQueries().fallbackToDestructiveMigration().build();
        userDao= db.getDao();



    }

    private void initView() {
        edtUserLogin=findViewById(R.id.edtUserNameLogin);
        edtPassLogin=findViewById(R.id.edtPasswordLogin);
        loginBtn=findViewById(R.id.loginBtn);

        makeAccount=findViewById(R.id.makeAccount);
        makeAccount.setOnClickListener(v -> {
            startActivity(new Intent(loginActivity.this,SinginActivity.class));
        });


    }

}