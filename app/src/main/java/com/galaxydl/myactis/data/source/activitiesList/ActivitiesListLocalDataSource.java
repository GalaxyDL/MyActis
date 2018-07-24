package com.galaxydl.myactis.data.source.activitiesList;

import android.content.Context;

import com.galaxydl.myactis.data.ActivitiesList;
import com.galaxydl.myactis.data.dao.ActivitiesListDao;
import com.galaxydl.myactis.data.source.ActivityDataBase;
import com.galaxydl.myactis.util.ExecutorHolder;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.Executor;

public class ActivitiesListLocalDataSource implements ActivitiesListDataSource {

    private static ActivitiesListLocalDataSource INSTANCE;

    private final ActivitiesListDao mActivitiesListDao;

    private final Executor mExecutor;

    private ActivitiesListLocalDataSource(Context context) {
        mActivitiesListDao = ActivityDataBase.getInstance(context.getApplicationContext()).getActivitiesListDao();
        mExecutor = ExecutorHolder.getExecutor();
    }

    public static ActivitiesListLocalDataSource getInstance(Context context) {
        if(INSTANCE == null) {
            INSTANCE = new ActivitiesListLocalDataSource(context);
        }
        return INSTANCE;
    }

    @Override
    public void listActivitiesLists(@NotNull final ListActivitiesListsListener listener) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                List<ActivitiesList> result = mActivitiesListDao.listActivitiesList();
                if(result.isEmpty()) {
                    listener.onFailed();
                }else {
                    listener.onSuccess(result);
                }
            }
        });
    }

    @Override
    public void getActivitiesList(@NotNull final String id, @NotNull final GetActivitiesListListener listListener) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                ActivitiesList result = mActivitiesListDao.getActivitiesListById(id);
                if(result == null) {
                    listListener.onFailed();
                }else {
                    listListener.onSuccess(result);
                }
            }
        });
    }

    @Override
    public void saveActivitiesList(@NotNull final ActivitiesList activitiesList) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mActivitiesListDao.addActivitiesList(activitiesList);
            }
        });
    }

    @Override
    public void complete(@NotNull final ActivitiesList activitiesList) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mActivitiesListDao.updateActivitiesList(activitiesList);
            }
        });
    }

    @Override
    public void redo(@NotNull final ActivitiesList activitiesList) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mActivitiesListDao.updateActivitiesList(activitiesList);
            }
        });
    }

    @Override
    public void deleteActivitiesList(@NotNull final ActivitiesList activitiesList) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mActivitiesListDao.deleteActivitiesList(activitiesList);
            }
        });
    }

    @Override
    public void deleteAllCompletedActivitiesLists() {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mActivitiesListDao.deleteAllCompletedActivitiesLists();
            }
        });
    }
}
