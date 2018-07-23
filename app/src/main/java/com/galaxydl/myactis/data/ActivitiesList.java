package com.galaxydl.myactis.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.galaxydl.myactis.data.converter.ListConverter;

import org.jetbrains.annotations.Contract;

import java.util.List;

@Entity(tableName = "activities_list")
@TypeConverters(ListConverter.class)
public final class ActivitiesList {
    @PrimaryKey
    private String id;

    private List<String> activities;

    private int state;

    public ActivitiesList(String id, List<String> activities) {
        this.id = id;
        this.activities = activities;
    }

    @Contract(pure = true)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Contract(pure = true)
    public List<String> getActivities() {
        return activities;
    }

    public void setActivities(List<String> activities) {
        this.activities = activities;
    }

    @Contract(pure = true)
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
