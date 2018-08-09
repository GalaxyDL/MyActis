package com.galaxydl.myactis;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    public static <T extends ViewModel> T obtainViewModel(
            FragmentActivity activity,
            Class<T> clazz) {
        return ViewModelProviders.of(
                activity,
                ViewModelFactory.getInstance(activity.getApplication())
        ).get(clazz);
    }

}
