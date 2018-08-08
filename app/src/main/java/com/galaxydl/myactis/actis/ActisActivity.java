package com.galaxydl.myactis.actis;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

import com.galaxydl.myactis.R;
import com.galaxydl.myactis.ViewModelFactory;
import com.galaxydl.myactis.util.ActivityUtils;

public class ActisActivity extends AppCompatActivity {

    private ActivitiesViewModel mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actis);

        setupViewFragment();

        mViewModel = obtainViewModel(this);
    }

    public static ActivitiesViewModel obtainViewModel(FragmentActivity activity) {
        return ViewModelProviders.of(
                activity,
                ViewModelFactory.getInstance(activity.getApplication())
        ).get(ActivitiesViewModel.class);
    }

    private void setupViewFragment() {
        ActivitiesFragment fragment =
                (ActivitiesFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.content_frame);
        if (fragment == null) {
            fragment = ActivitiesFragment.newInstance();
            ActivityUtils.replaceFragmentInActivity(
                    getSupportFragmentManager(), fragment, R.id.content_frame);
        }
    }

}