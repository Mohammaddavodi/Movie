package com.example.blufilm.DataBase;

import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {UserTable.class},version = 1)
public abstract class Database extends RoomDatabase {
    public abstract UserDao getDao();

}
