package com.galaxydl.myactis.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import org.jetbrains.annotations.Contract;

@Entity(tableName = "activities",
        foreignKeys = @ForeignKey(entity = Activity.class,
                parentColumns = "id",
                childColumns = "precursor"))
public final class Activity {
    @PrimaryKey
    private String id;

    private String description;

    private String precursor;

    private int state;

    public Activity(String id, String description, String precursor, int state) {
        this.id = id;
        this.description = description;
        this.precursor = precursor;
        this.state = state;
    }

    @Contract(pure = true)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Contract(pure = true)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Contract(pure = true)
    public String getPrecursor() {
        return precursor;
    }

    public void setPrecursor(String precursor) {
        this.precursor = precursor;
    }

    @Contract(pure = true)
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
