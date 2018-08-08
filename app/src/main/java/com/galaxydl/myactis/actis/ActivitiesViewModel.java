package com.galaxydl.myactis.actis;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import com.galaxydl.myactis.SingleLiveEvent;
import com.galaxydl.myactis.data.Activity;
import com.galaxydl.myactis.data.source.activity.ActivityRepository;

import java.util.List;

public class ActivitiesViewModel
        extends AndroidViewModel
        implements ActivityRepository.ListActivitiesListener {

    public final ObservableBoolean empty = new ObservableBoolean();

    public final ObservableList<Activity> activities = new ObservableArrayList<>();

    private final SingleLiveEvent<Void> mAddActivityCommand = new SingleLiveEvent<>();

    @SuppressLint("StaticFieldLeak")
    private final Context mContext;

    private final ActivityRepository mActivityRepository;

    public ActivitiesViewModel(@NonNull Application application,
                               ActivityRepository activityRepository) {
        super(application);
        mContext = application.getApplicationContext();
        mActivityRepository = activityRepository;
    }

    public SingleLiveEvent<Void> getAddActivityCommand() {
        return mAddActivityCommand;
    }

    public void start() {
        mActivityRepository.listActivities(this);
    }

    @Override
    public void onSuccess(List<Activity> activities) {
        if (activities.isEmpty()) {
            empty.set(true);
        } else {
            this.activities.addAll(activities);
        }
    }

    @Override
    public void onFailed() {
        empty.set(true);
    }
}
