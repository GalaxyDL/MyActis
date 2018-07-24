package com.galaxydl.myactis.data.source;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.galaxydl.myactis.data.ActivitiesFlow;
import com.galaxydl.myactis.data.ActivitiesList;
import com.galaxydl.myactis.data.Activity;
import com.galaxydl.myactis.data.dao.ActivitiesFlowDao;
import com.galaxydl.myactis.data.dao.ActivitiesListDao;
import com.galaxydl.myactis.data.dao.ActivityDao;

@Database(entities = {Activity.class,
            ActivitiesFlow.class,
            ActivitiesList.class},
        version = 1)
public abstract class ActivityDataBase extends RoomDatabase {
    private static final String DATABASE_NAME = "activity.db";
    private static ActivityDataBase INSTANCE;

    private static final Object lock = new Object();

    public static ActivityDataBase getInstance(Context context) {
        synchronized (lock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        ActivityDataBase.class,
                        DATABASE_NAME).build();
            }
        }
        return INSTANCE;
    }

    public abstract ActivityDao getActivityDao();

    public abstract ActivitiesListDao getActivitiesListDao();

    public abstract ActivitiesFlowDao getActivitiesFlowDao();
}
