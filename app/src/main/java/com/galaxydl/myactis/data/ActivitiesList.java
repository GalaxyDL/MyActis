package com.galaxydl.myactis.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.galaxydl.myactis.data.converter.ListConverter;

import org.jetbrains.annotations.Contract;

import java.util.List;
import java.util.UUID;

@Entity(tableName = "activities_list")
@TypeConverters(ListConverter.class)
public final class ActivitiesList implements CacheAble{

    @Ignore
    public static final int STATE_UNCOMPLETED = 1;
    @Ignore
    public static final int STATE_COMPLETED = 2;

    @PrimaryKey
    private String id;

    private List<String> activities;

    private int state;

    public ActivitiesList(String id, List<String> activities, int state) {
        this.id = id;
        this.activities = activities;
        this.state = state;
    }

    public ActivitiesList(List<String> activities) {
        this(UUID.randomUUID().toString(), activities, STATE_UNCOMPLETED);
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
