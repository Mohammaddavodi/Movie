package com.example.blufilm.Api;

import androidx.room.TypeConverter;

import java.util.Arrays;
import java.util.List;

public class StringListConverter {

    @TypeConverter
    public static String fromList(List<String> actors) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String actor : actors) {
            stringBuilder.append(actor).append(",");
        }
        return stringBuilder.toString().trim();
    }

    @TypeConverter
    public static List<String> toList(String actorsString) {
        return Arrays.asList(actorsString.split(","));
    }
}
