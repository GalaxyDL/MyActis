package com.galaxydl.myactis.addActivity;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.support.annotation.NonNull;

import com.galaxydl.myactis.data.source.activity.ActivityRepository;

public class AddActivityViewModel extends AndroidViewModel {

    @SuppressLint("StaticFieldLeak")
    private final Context mContext;

    private final ActivityRepository mActivityRepository;

    public AddActivityViewModel(@NonNull Application application,
                                ActivityRepository activityRepository) {
        super(application);
        mContext = application.getApplicationContext();
        mActivityRepository = activityRepository;
    }
}
