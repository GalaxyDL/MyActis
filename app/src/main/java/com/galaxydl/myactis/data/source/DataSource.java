package com.galaxydl.myactis.data.source;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

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
public abstract class DataSource extends RoomDatabase {

    public abstract ActivityDao getActivityDao();

    public abstract ActivitiesListDao getActivitiesListDao();

    public abstract ActivitiesFlowDao getActivitiesFlowDao();
}
