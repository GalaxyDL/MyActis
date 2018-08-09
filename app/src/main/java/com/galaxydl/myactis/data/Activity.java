package com.galaxydl.myactis.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import org.jetbrains.annotations.Contract;

import java.util.Objects;
import java.util.UUID;

@Entity(tableName = "activities",
        foreignKeys = @ForeignKey(entity = Activity.class,
                parentColumns = "id",
                childColumns = "precursor"))
public final class Activity implements CacheAble{

    @Ignore
    public static final int STATE_UNCOMPLETED = 1;
    @Ignore
    public static final int STATE_COMPLETED = 2;
    @Ignore
    public static final int STATE_SKIPPED = 3;

    @PrimaryKey
    private String id;

    private String title;

    private String description;

    private String precursor;

    private int state;

    public Activity(String id, String title, String description, String precursor, int state) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.precursor = precursor;
        this.state = state;
    }

    public Activity(String title, String description, String precursor) {
        this(UUID.randomUUID().toString(), title, description, precursor, STATE_UNCOMPLETED);
    }

    @Contract(pure = true)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Contract(pure = true)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Activity activity = (Activity) o;
        return state == activity.state &&
                Objects.equals(id, activity.id) &&
                Objects.equals(title, activity.title) &&
                Objects.equals(description, activity.description) &&
                Objects.equals(precursor, activity.precursor);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title, description, precursor, state);
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id='" + id + '\'' +
                ", title=" + title + '\'' +
                ", description='" + description + '\'' +
                ", precursor='" + precursor + '\'' +
                ", state=" + state +
                '}';
    }
}
