package com.galaxydl.myactis.actis;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.galaxydl.myactis.R;
import com.galaxydl.myactis.data.Activity;
import com.galaxydl.myactis.databinding.ActivityItemBinding;

import java.util.List;

public class ActivitiesAdapter extends RecyclerView.Adapter<ActivitiesAdapter.ActivityViewHolder> {

    private List<Activity> mActivityList;

    private LayoutInflater mInflater;

    ActivitiesAdapter(List<Activity> activityList, Context context) {
        mActivityList = activityList;
        mInflater = LayoutInflater.from(context);
    }

    public List<Activity> getActivityList() {
        return mActivityList;
    }

    @NonNull
    @Override
    public ActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ActivityItemBinding binding = DataBindingUtil.inflate(
                mInflater,
                R.layout.activity_item,
                parent,
                false);
        return new ActivityViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityViewHolder holder, int position) {
        ActivityItemBinding binding = holder.mBinding;
        binding.setActivity(mActivityList.get(position));
    }

    @Override
    public int getItemCount() {
        return mActivityList == null ? 0 : mActivityList.size();
    }

    static class ActivityViewHolder extends RecyclerView.ViewHolder {

        private ActivityItemBinding mBinding;

        ActivityViewHolder(ActivityItemBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }
}
