package com.galaxydl.myactis.data.source.activity;

import android.content.Context;

import com.galaxydl.myactis.data.Activity;
import com.galaxydl.myactis.data.dao.ActivityDao;
import com.galaxydl.myactis.data.source.ActivityDataBase;
import com.galaxydl.myactis.util.ExecutorHolder;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.Executor;

public class ActivityLocalDataSource implements ActivityDataSource {

    private static ActivityLocalDataSource INSTANCE;

    private final ActivityDao mActivityDao;

    private final Executor mExecutor;

    private ActivityLocalDataSource(Context context) {
        mActivityDao = ActivityDataBase.getInstance(context.getApplicationContext()).getActivityDao();
        mExecutor = ExecutorHolder.getExecutor();
    }

    public static ActivityLocalDataSource getInstance(Context context) {
        if(INSTANCE == null) {
            INSTANCE = new ActivityLocalDataSource(context);
        }
        return INSTANCE;
    }

    @Override
    public void listActivities(@NotNull final ListActivitiesListener listener) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                List<Activity> result = mActivityDao.listActivities();
                if(result.isEmpty()){
                    listener.onFailed();
                }else {
                    listener.onSuccess(result);
                }
            }
        });
    }

    @Override
    public void getActivity(@NotNull final String id, @NotNull final GetActivitiesListener listener) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                Activity result = mActivityDao.getActivityById(id);
                if(result == null) {
                    listener.onFailed();
                }else {
                    listener.onSuccess(result);
                }
            }
        });
    }

    @Override
    public void saveActivity(@NotNull final Activity activity) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mActivityDao.addActivity(activity);
            }
        });
    }

    @Override
    public void complete(@NotNull final Activity activity) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                activity.setState(Activity.STATE_COMPLETED);
                mActivityDao.updateActivity(activity);
            }
        });
    }

    @Override
    public void redo(@NotNull final Activity activity) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                activity.setState(Activity.STATE_UNCOMPLETED);
                mActivityDao.updateActivity(activity);
            }
        });
    }

    @Override
    public void skip(@NotNull final Activity activity) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                activity.setState(Activity.STATE_SKIPPED);
                mActivityDao.updateActivity(activity);
            }
        });
    }

    @Override
    public void deleteActivity(@NotNull final Activity activity) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mActivityDao.deleteActivity(activity);
            }
        });
    }

    @Override
    public void deleteAllCompletedActivities() {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mActivityDao.deleteAllCompletedActivities();
            }
        });
    }
}
