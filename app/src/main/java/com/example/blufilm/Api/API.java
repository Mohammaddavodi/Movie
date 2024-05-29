// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

package com.example.blufilm.Api;
import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import java.util.Arrays;
import java.util.List;


@Entity(tableName = "favMovie")
public class API {
    @PrimaryKey
    @NonNull
    private String name;
    @TypeConverters(StringListConverter.class)
    private List<String> actors;
    private String thumburl;
    private long year;
    private String imageurl;

    @TypeConverters(StringListConverter.class)
    private List<String> directors;

    @TypeConverters(StringListConverter.class)
    private List<String> genre;
    private double rating;
    private String imdburl;
    private String desc;

    public API(String name,String thumburl,List<String> actors,
               long year, String imageurl,
               List<String> directors,List<String> genre,
               double rating, String imdburl, String desc) {
        this.actors = actors;
        this.thumburl = thumburl;
        this.year = year;
        this.imageurl = imageurl;
        this.directors = directors;
        this.genre = genre;
        this.name = name;
        this.rating = rating;
        this.imdburl = imdburl;
        this.desc = desc;
    }

    public List<String> getActors() { return actors; }
    public void setActors(List<String> value) { this.actors = value; }

    public String getThumburl() { return thumburl; }
    public void setThumburl(String value) { this.thumburl = value; }

    public long getYear() { return year; }
    public void setYear(long value) { this.year = value; }

    public String getImageurl() { return imageurl; }
    public void setImageurl(String value) { this.imageurl = value; }

    public List<String> getDirectors() { return directors; }
    public void setDirectors(List<String> value) { this.directors = value; }

    public List<String> getGenre() { return genre; }
    public void setGenre(List<String> value) { this.genre = value; }

    public String getName() { return name; }
    public void setName(String value) { this.name = value; }

    public double getRating() { return rating; }
    public void setRating(double value) { this.rating = value; }

    public String getImdburl() { return imdburl; }
    public void setImdburl(String value) { this.imdburl = value; }

    public String getDesc() { return desc; }
    public void setDesc(String value) { this.desc = value; }
}
