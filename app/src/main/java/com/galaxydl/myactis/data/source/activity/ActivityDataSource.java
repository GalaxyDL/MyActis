package com.galaxydl.myactis.data.source.activity;

import com.galaxydl.myactis.data.Activity;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface ActivityDataSource {

    interface ListActivitiesListener {

        void onSuccess(List<Activity> activities);

        void onFailed();
    }

    interface GetActivitiesListener {

        void onSuccess(Activity activity);

        void onFailed();
    }

    void listActivities(@NotNull ListActivitiesListener listener);

    void getActivity(@NotNull String id, @NotNull GetActivitiesListener listener);

    void saveActivity(@NotNull Activity activity);

    void complete(@NotNull Activity activity);

    void redo(@NotNull Activity activity);

    void skip(@NotNull Activity activity);

    void deleteActivity(@NotNull Activity activity);

    void deleteAllCompletedActivities();
}
