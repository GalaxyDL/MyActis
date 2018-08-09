package com.galaxydl.myactis.addActivity;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.galaxydl.myactis.R;
import com.galaxydl.myactis.databinding.AddActivityFragBinding;

public class AddActivityFragment extends Fragment {

    private AddActivityFragBinding mBinding;

    private AddActivityViewModel mViewModel;

    static AddActivityFragment newInstance() {
        return new AddActivityFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = AddActivityFragBinding.inflate(inflater, container, false);
        mViewModel = AddActiActivity.obtainViewModel(getActivity(), AddActivityViewModel.class);

        setupSaveBtn();

        return mBinding.getRoot();
    }


    private void setupSaveBtn() {
        mBinding.saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.saveActivity();
            }
        });
    }

}
