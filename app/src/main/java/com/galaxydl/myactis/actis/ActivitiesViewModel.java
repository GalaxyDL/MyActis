package com.galaxydl.myactis.actis;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.support.annotation.NonNull;

import com.galaxydl.myactis.data.source.activity.ActivityRepository;

public class ActivitiesViewModel extends AndroidViewModel {

    @SuppressLint("StaticFieldLeak")
    private final Context mContext;

    private final ActivityRepository mActivityRepository;

    public ActivitiesViewModel(@NonNull Application application,
                               ActivityRepository activityRepository) {
        super(application);
        mContext = application.getApplicationContext();
        mActivityRepository = activityRepository;
    }
}
