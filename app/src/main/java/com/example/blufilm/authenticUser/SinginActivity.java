package com.example.blufilm.authenticUser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.blufilm.Activity.MainActivity;
import com.example.blufilm.DataBase.Database;
import com.example.blufilm.DataBase.UserDao;
import com.example.blufilm.DataBase.UserTable;
import com.example.blufilm.R;

public class SinginActivity extends AppCompatActivity {
    private Database myDb;
    private UserDao userDao;
    private EditText userEdt,passEdt;
    private TextView logAccount;

    private Button loginBtn;
    public static boolean isAllowed=false;
    private UserManager userManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singin);
        initSingin();
        userManager=new UserManager(this);

        myDb= Room.databaseBuilder(this, Database.class,"userTable")
                .allowMainThreadQueries().fallbackToDestructiveMigration().build();
        userDao=myDb.getDao();




    }



    private void initSingin(){
        userEdt=findViewById(R.id.edtUserNameLogin);
        passEdt=findViewById(R.id.edtPasswordLogin);
        loginBtn=findViewById(R.id.loginBtn);
        logAccount=findViewById(R.id.loginAccount);
        logAccount.setOnClickListener(v -> {
            startActivity(new Intent(SinginActivity.this, loginActivity.class));
        });

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
            }if (userEdt.getText().toString().isEmpty() || passEdt.getText().toString().isEmpty()){
                Toast.makeText(this, "fill fiedls", Toast.LENGTH_SHORT).show();
            }else {
                startActivity(new Intent(SinginActivity.this, loginActivity.class));
            }
//            userManager.saveNewUserAccount(userEdt,passEdt);
//           userEdt.setText(userManager.getEmail());
//           passEdt.setText(userManager.getPassword());

        });


    }
}