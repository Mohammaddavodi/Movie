package com.example.blufilm.DataBase;

import androidx.room.RoomDatabase;

import com.example.blufilm.Api.API;

@androidx.room.Database(entities = {API.class},version = 1)
public abstract class FavDatabase extends RoomDatabase {
    public abstract FavDao getFavDao();
}
