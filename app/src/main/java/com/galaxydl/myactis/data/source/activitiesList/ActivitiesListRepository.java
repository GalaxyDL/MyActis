package com.galaxydl.myactis.data.source.activitiesList;

import android.content.Context;

import com.galaxydl.myactis.data.ActivitiesList;
import com.galaxydl.myactis.data.MemoryCache;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ActivitiesListRepository implements ActivitiesListDataSource {

    private ActivitiesListDataSource mLocalDataSource;

    private MemoryCache<ActivitiesList> mCache;

    public ActivitiesListRepository(Context context) {
        mLocalDataSource = ActivitiesListLocalDataSource.getInstance(context);
        mCache = new MemoryCache<>();
    }

    @Override
    public void listActivitiesLists(@NotNull final ListActivitiesListsListener listener) {
        List<ActivitiesList> cacheList = mCache.getAll();
        if(cacheList.isEmpty()) {
            mLocalDataSource.listActivitiesLists(new ListActivitiesListsListener() {
                @Override
                public void onSuccess(List<ActivitiesList> activitiesLists) {
                    mCache.putAll(activitiesLists);
                    listener.onSuccess(activitiesLists);
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
    public void getActivitiesList(@NotNull String id, @NotNull final GetActivitiesListListener listListener) {
        ActivitiesList cacheActivitiesList = mCache.get(id);
        if(cacheActivitiesList == null) {
            mLocalDataSource.getActivitiesList(id, new GetActivitiesListListener() {
                @Override
                public void onSuccess(ActivitiesList activitiesList) {
                    mCache.put(activitiesList);
                    listListener.onSuccess(activitiesList);
                }

                @Override
                public void onFailed() {
                    listListener.onFailed();
                }
            });
        }else {
            listListener.onSuccess(cacheActivitiesList);
        }
    }

    @Override
    public void saveActivitiesList(@NotNull ActivitiesList activitiesList) {
        mLocalDataSource.saveActivitiesList(activitiesList);
        mCache.put(activitiesList);
    }

    @Override
    public void complete(@NotNull ActivitiesList activitiesList) {
        activitiesList.setState(ActivitiesList.STATE_COMPLETED);
        mLocalDataSource.complete(activitiesList);
        mCache.put(activitiesList);
    }

    @Override
    public void redo(@NotNull ActivitiesList activitiesList) {
        activitiesList.setState(ActivitiesList.STATE_UNCOMPLETED);
        mLocalDataSource.redo(activitiesList);
        mCache.put(activitiesList);
    }

    @Override
    public void deleteActivitiesList(@NotNull ActivitiesList activitiesList) {
        mLocalDataSource.deleteActivitiesList(activitiesList);
        mCache.delete(activitiesList);
    }

    @Override
    public void deleteAllCompletedActivitiesLists() {
        mLocalDataSource.deleteAllCompletedActivitiesLists();
        List<ActivitiesList> cacheList = mCache.getAll();
        for(ActivitiesList activitiesList: cacheList) {
            if(activitiesList.getState() == ActivitiesList.STATE_COMPLETED) {
                mCache.delete(activitiesList);
            }
        }
    }
}
