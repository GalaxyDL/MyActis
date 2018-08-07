package com.galaxydl.myactis;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.galaxydl.myactis.actis.ActivitiesViewModel;
import com.galaxydl.myactis.addActivity.AddActivityViewModel;
import com.galaxydl.myactis.data.source.activitiesFlow.ActivitiesFlowRepository;
import com.galaxydl.myactis.data.source.activity.ActivityRepository;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private static volatile ViewModelFactory INSTANCE;

    private final Application mApplication;

    private final ActivityRepository mActivityRepository;

    private final ActivitiesFlowRepository mActivitiesFlowRepository;

    public static ViewModelFactory getInstance(Application application) {
        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ViewModelFactory(application,
                            new ActivityRepository(application.getApplicationContext()),
                            new ActivitiesFlowRepository(application.getApplicationContext()));
                }
            }
        }
        return INSTANCE;
    }

    private ViewModelFactory(Application mApplication,
                             ActivityRepository mActivityRepository,
                             ActivitiesFlowRepository mActivitiesFlowRepository) {
        this.mApplication = mApplication;
        this.mActivityRepository = mActivityRepository;
        this.mActivitiesFlowRepository = mActivitiesFlowRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ActivitiesViewModel.class)) {
            //noinspection unchecked
            return (T) new ActivitiesViewModel(mApplication, mActivityRepository);
        } else if (modelClass.isAssignableFrom(AddActivityViewModel.class)) {
            //noinspection unchecked
            return (T) new AddActivityViewModel(mApplication, mActivityRepository);
        }
        return super.create(modelClass);
    }
}
