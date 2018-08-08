package com.galaxydl.myactis.actis;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.ObservableList;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.galaxydl.myactis.ViewModelFactory;
import com.galaxydl.myactis.data.Activity;
import com.galaxydl.myactis.databinding.ActivitiesFragBinding;

import java.util.ArrayList;

public class ActivitiesFragment extends Fragment {

    private ActivitiesFragBinding mBinding;

    private ActivitiesViewModel mViewModel;

    private RecyclerView mRecyclerView;

    private ActivitiesAdapter mAdapter;

    public static ActivitiesFragment newInstance() {
        return new ActivitiesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        mBinding = ActivitiesFragBinding.inflate(inflater, container, false);
        mViewModel = ViewModelProviders.of(
                this,
                ViewModelFactory.getInstance(getActivity().getApplication())
        ).get(ActivitiesViewModel.class);
        mBinding.setViewModel(mViewModel);

        setupRecyclerView();

        return mBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        mViewModel.start();
    }

    private void setupRecyclerView() {
        mRecyclerView = mBinding.activitiesRV;

        mAdapter = new ActivitiesAdapter(new ArrayList<Activity>(), getContext());
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mViewModel.activities.addOnListChangedCallback(
                new ObservableList.OnListChangedCallback<ObservableList<Activity>>() {
                    @Override
                    public void onChanged(ObservableList<Activity> sender) {
                        mAdapter.getActivityList().clear();
                        mAdapter.getActivityList().addAll(sender);
                    }

                    @Override
                    public void onItemRangeChanged(
                            ObservableList<Activity> sender,
                            int positionStart,
                            int itemCount) {
                        onItemRangeRemoved(sender, positionStart, itemCount);
                        onItemRangeInserted(sender, positionStart, itemCount);
                    }

                    @Override
                    public void onItemRangeInserted(
                            ObservableList<Activity> sender,
                            int positionStart,
                            int itemCount) {
                        mAdapter.getActivityList().addAll(
                                positionStart, sender.subList(positionStart, positionStart + itemCount)
                        );
                    }

                    @Override
                    public void onItemRangeMoved(
                            ObservableList<Activity> sender,
                            int fromPosition,
                            int toPosition,
                            int itemCount) {
                        onItemRangeRemoved(sender, fromPosition, itemCount);
                        onItemRangeInserted(sender, toPosition, itemCount);
                    }

                    @Override
                    public void onItemRangeRemoved(
                            ObservableList<Activity> sender,
                            int positionStart,
                            int itemCount) {
                        mAdapter.getActivityList().removeAll(
                                mAdapter.getActivityList().subList(
                                        positionStart,
                                        positionStart + itemCount
                                )
                        );
                    }
                });
    }

}
