package com.galaxydl.myactis.data.source.activitiesList;

import com.galaxydl.myactis.data.ActivitiesList;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface ActivitiesListDataSource {

    interface ListActivitiesListsListener {

        void onSuccess(List<ActivitiesList> activitiesLists);

        void onFailed();
    }

    interface GetActivitiesListListener {

        void onSuccess(ActivitiesList activitiesList);

        void onFailed();
    }

    void listActivitiesLists(@NotNull ListActivitiesListsListener listener);

    void getActivitiesList(@NotNull String id, @NotNull GetActivitiesListListener listListener);

    void saveActivitiesList(@NotNull ActivitiesList activitiesList);

    void complete(@NotNull ActivitiesList activitiesList);

    void redo(@NotNull ActivitiesList activitiesList);

    void deleteActivitiesList(@NotNull ActivitiesList activitiesList);

    void deleteAllCompletedActivitiesLists();
}
