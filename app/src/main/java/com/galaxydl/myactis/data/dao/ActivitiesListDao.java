package com.galaxydl.myactis.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.galaxydl.myactis.data.ActivitiesList;

import java.util.List;

@Dao
public interface ActivitiesListDao {

    @Query("select * from activities_list")
    List<ActivitiesList> listActivitiesList();

    @Query("select * from activities_list where id = :id")
    ActivitiesList getActivitiesListById(String id);

    @Insert
    void addActivitiesList(ActivitiesList activitiesList);

    @Update
    void updateActivitiesList(ActivitiesList activitiesList);

    @Delete
    void deleteActivitiesList(ActivitiesList activitiesList);

    @Query("delete from activities_list where state = " + ActivitiesList.STATE_COMPLETED)
    void deleteAllCompletedActivitiesLists();
}
