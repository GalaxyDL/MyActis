package com.galaxydl.myactis.data.converter;

import android.arch.persistence.room.TypeConverter;

import java.util.Arrays;
import java.util.List;

public class ListConverter {
    private final static String SEPARATOR = "/";

    @TypeConverter
    public static String listToString(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for(String s: list) {
            sb.append(s);
            sb.append(SEPARATOR);
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    @TypeConverter
    public static List<String> toList(String string) {
        return Arrays.asList(string.split(SEPARATOR));
    }
}
