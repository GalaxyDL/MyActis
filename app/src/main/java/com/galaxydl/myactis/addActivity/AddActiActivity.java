package com.galaxydl.myactis.addActivity;

import android.os.Bundle;

import com.galaxydl.myactis.BaseActivity;
import com.galaxydl.myactis.R;
import com.galaxydl.myactis.util.ActivityUtils;

public class AddActiActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_acti);

        setupViewFragment();
    }

    private void setupViewFragment() {
        AddActivityFragment fragment =
                (AddActivityFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.content_frame);
        if (fragment == null) {
            fragment = AddActivityFragment.newInstance();
            ActivityUtils.replaceFragmentInActivity(
                    getSupportFragmentManager(), fragment, R.id.content_frame);
        }
    }
}
