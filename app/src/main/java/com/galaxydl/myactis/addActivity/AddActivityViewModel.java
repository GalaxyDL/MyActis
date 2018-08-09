package com.galaxydl.myactis.addActivity;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.galaxydl.myactis.SingleLiveEvent;
import com.galaxydl.myactis.data.Activity;
import com.galaxydl.myactis.data.source.activity.ActivityRepository;

public class AddActivityViewModel extends AndroidViewModel {

    public final ObservableField<String> title = new ObservableField<>();

    public final ObservableField<String> description = new ObservableField<>();

    public final ObservableField<String> precursor = new ObservableField<>();

    @SuppressLint("StaticFieldLeak")
    private final Context mContext;

    private final ActivityRepository mActivityRepository;

    public AddActivityViewModel(@NonNull Application application,
                                ActivityRepository activityRepository) {
        super(application);
        mContext = application.getApplicationContext();
        mActivityRepository = activityRepository;
    }

    public void saveActivity() {
        Activity newActivity = new Activity(title.get(), description.get(), precursor.get());
        mActivityRepository.saveActivity(newActivity);
    }

}
