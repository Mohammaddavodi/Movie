package com.example.blufilm.authenticUser;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.EditText;

import androidx.annotation.Nullable;

public class UserManager {
    private SharedPreferences sharedPreferences;
    public UserManager(Context context){
        sharedPreferences=context.getSharedPreferences("userName",Context.MODE_PRIVATE);

    }



    public boolean saveUserAccount(String email,
                                   String password){
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putString("email",email);
        editor.putString("password",password);
        editor.apply();
        return true;
    }
    public void saveNewUserAccount(EditText email,
                                   EditText password){
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putString("email", String.valueOf(email));
        editor.putString("password", String.valueOf(password));
        editor.apply();
        Log.d("TAG", "saveNewUserAccount: new "+email.toString());
    }
    public String getEmail(){
        return sharedPreferences.getString("LoginEmail",null);
    }
    public String getPassword(){
        return sharedPreferences.getString("LoginPassword",null);
    }
}
