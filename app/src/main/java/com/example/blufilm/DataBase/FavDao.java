package com.example.blufilm.DataBase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.blufilm.Api.API;

import java.util.List;

@Dao
public interface FavDao {
    @Insert
    void addToFavorite(API api);
    @Query("SELECT EXISTS (SELECT * FROM favMovie where name=:name)")
        //List<API>  getAllFavoriteMovie();
    String nameFav (String name);
    @Delete
    void deleteFacoriteMovie(API api);
}
