package com.galaxydl.myactis.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.galaxydl.myactis.data.ActivitiesFlow;

import java.util.List;

@Dao
public interface ActivitiesFlowDao {

    @Query("select * from activities_flow")
    List<ActivitiesFlow> listActivitiesFlow();

    @Query("select * from activities_flow where id = :id")
    ActivitiesFlow getActivitiesFlowById(String id);

    @Insert
    void addActivityFlow(ActivitiesFlow activitiesFlow);

    @Update
    void updateActivityFlow(ActivitiesFlow activitiesFlow);

    @Delete
    void deleteActivityFlow(ActivitiesFlow activitiesFlow);
}
