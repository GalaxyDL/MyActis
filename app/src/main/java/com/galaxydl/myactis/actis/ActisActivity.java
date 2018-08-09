package com.galaxydl.myactis.actis;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

import com.galaxydl.myactis.BaseActivity;
import com.galaxydl.myactis.R;
import com.galaxydl.myactis.addActivity.AddActiActivity;
import com.galaxydl.myactis.util.ActivityUtils;

public class ActisActivity extends BaseActivity {

    private ActivitiesViewModel mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actis);

        mViewModel.getAddActivityCommand().observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void aVoid) {
                addActivity();
            }
        });

        setupViewFragment();

        setupFAB();

        mViewModel = obtainViewModel(this, ActivitiesViewModel.class);
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

    private void setupFAB() {
        FloatingActionButton actionButton = findViewById(R.id.fab_add_activity);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.addActivity();
            }
        });
    }

    private void addActivity() {
        Intent intent = new Intent(this, AddActiActivity.class);
        startActivity(intent);
    }

}