package com.galaxydl.myactis.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.galaxydl.myactis.data.Activity;

import java.util.List;

@Dao
public interface ActivityDao {

    @Query("select * from activities")
    List<Activity> listActivities();

    @Query("select * from activities where id = :id")
    Activity getActivityById(String id);

    @Insert
    void addActivity(Activity activity);

    @Update
    void updateActivity(Activity activity);

    @Delete
    void deleteActivity(Activity activity);
}
