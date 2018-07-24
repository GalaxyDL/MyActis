package com.galaxydl.myactis.data.source;

import android.content.Context;

import com.galaxydl.myactis.data.Activity;
import com.galaxydl.myactis.data.MemoryCache;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ActivityRepository implements ActivityDataSource {

    private ActivityDataSource localDataSource;

    private MemoryCache<Activity> mCache;

    public ActivityRepository(Context context) {
        localDataSource = ActivityLocalDataSource.getInstance(context);
        mCache = new MemoryCache<>();
    }

    @Override
    public void listActivities(@NotNull final ListActivitiesListener listener) {
        List<Activity> cacheList = mCache.getAll();
        if(cacheList.isEmpty()){
            localDataSource.listActivities(new ListActivitiesListener() {
                @Override
                public void onSuccess(List<Activity> activities) {
                    mCache.putAll(activities);
                    listener.onSuccess(activities);
                }

                @Override
                public void onFailed() {
                    listener.onFailed();
                }
            });
        }else {
            listener.onSuccess(cacheList);
        }
    }

    @Override
    public void getActivity(@NotNull String id, @NotNull final GetActivitiesListener listener) {
        Activity cacheActivity = mCache.get(id);
        if(cacheActivity == null) {
            localDataSource.getActivity(id, new GetActivitiesListener() {
                @Override
                public void onSuccess(Activity activity) {
                    mCache.put(activity);
                    listener.onSuccess(activity);
                }

                @Override
                public void onFailed() {
                    listener.onFailed();
                }
            });
        }else {
            listener.onSuccess(cacheActivity);
        }
    }

    @Override
    public void saveActivity(@NotNull Activity activity) {
        localDataSource.saveActivity(activity);
        mCache.put(activity);
    }

    @Override
    public void complete(@NotNull Activity activity) {
        activity.setState(Activity.STATE_COMPLETED);
        localDataSource.complete(activity);
        mCache.put(activity);
    }

    @Override
    public void redo(@NotNull Activity activity) {
        activity.setState(Activity.STATE_UNCOMPLETED);
        localDataSource.redo(activity);
        mCache.put(activity);
    }

    @Override
    public void skip(@NotNull Activity activity) {
        activity.setState(Activity.STATE_SKIPPED);
        localDataSource.skip(activity);
        mCache.put(activity);
    }

    @Override
    public void deleteActivity(@NotNull Activity activity) {
        localDataSource.deleteActivity(activity);
        mCache.delete(activity);
    }

    @Override
    public void deleteAllCompletedActivities() {
        localDataSource.deleteAllCompletedActivities();
        List<Activity> cacheList = mCache.getAll();
        for(Activity activity: cacheList) {
            if(activity.getState() == Activity.STATE_COMPLETED){
                mCache.delete(activity);
            }
        }
    }
}
