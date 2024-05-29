package com.example.blufilm.DataBase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.blufilm.Api.API;

@Dao
public interface UserDao {
    @Insert
    void insertUser(UserTable userTable);
    @Query("SELECT EXISTS (SELECT * FROM usertable where userName=:userName)")
    boolean is_taken (String userName);

    @Query("SELECT EXISTS (SELECT * FROM usertable where userName=:userName AND password=:password)")
    boolean login (String userName,String password);

    @Query("SELECT * FROM userTable WHERE username = :Username LIMIT 1")
    UserTable getUserByUsername(String Username);



}
