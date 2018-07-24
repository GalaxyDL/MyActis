package com.galaxydl.myactis.data.source.activitiesFlow;

import android.content.Context;

import com.galaxydl.myactis.data.ActivitiesFlow;
import com.galaxydl.myactis.data.MemoryCache;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ActivitiesFlowRepository implements ActivitiesFlowDataSource {

    private ActivitiesFlowsLocalDataSource mLocalDataSource;

    private MemoryCache<ActivitiesFlow> mCache;

    public ActivitiesFlowRepository(Context context) {
        mLocalDataSource = ActivitiesFlowsLocalDataSource.getInstance(context);
        mCache = new MemoryCache<>();
    }

    @Override
    public void listActivitiesFlows(@NotNull final ListActivitiesFlowsListener listener) {
        List<ActivitiesFlow> cacheList = mCache.getAll();
        if(cacheList.isEmpty()) {
            mLocalDataSource.listActivitiesFlows(new ListActivitiesFlowsListener() {
                @Override
                public void onSuccess(List<ActivitiesFlow> activitiesFlows) {
                    mCache.putAll(activitiesFlows);
                    listener.onSuccess(activitiesFlows);
                }

                @Override
                public void onFailed() {
                    listener.onFailed();
                }
            });
        }else {
            listener.onSuccess(cacheList);
        }
    }

    @Override
    public void getActivitiesFlow(@NotNull String id, @NotNull final GetActivitiesFlowListener listener) {
        ActivitiesFlow cacheActivitiesFlow = mCache.get(id);
        if(cacheActivitiesFlow == null) {
            mLocalDataSource.getActivitiesFlow(id, new GetActivitiesFlowListener() {
                @Override
                public void onSuccess(ActivitiesFlow activitiesFlow) {
                    mCache.put(activitiesFlow);
                    listener.onSuccess(activitiesFlow);
                }

                @Override
                public void onFailed() {
                    listener.onFailed();
                }
            });
        }else {
            listener.onSuccess(cacheActivitiesFlow);
        }
    }

    @Override
    public void saveActivitiesFlow(@NotNull ActivitiesFlow activitiesFlow) {
        mLocalDataSource.saveActivitiesFlow(activitiesFlow);
        mCache.put(activitiesFlow);
    }

    @Override
    public void complete(@NotNull ActivitiesFlow activitiesFlow) {
        activitiesFlow.setState(ActivitiesFlow.STATE_COMPLETED);
        mLocalDataSource.complete(activitiesFlow);
        mCache.put(activitiesFlow);
    }

    @Override
    public void redo(@NotNull ActivitiesFlow activitiesFlow) {
        activitiesFlow.setState(ActivitiesFlow.STATE_UNCOMPLETED);
        mLocalDataSource.redo(activitiesFlow);
        mCache.put(activitiesFlow);
    }

    @Override
    public void deleteActivitiesFlow(@NotNull ActivitiesFlow activitiesFlow) {
        mLocalDataSource.deleteActivitiesFlow(activitiesFlow);
        mCache.delete(activitiesFlow);
    }

    @Override
    public void deleteAllCompletedActivitiesFlows() {
        mLocalDataSource.deleteAllCompletedActivitiesFlows();
        List<ActivitiesFlow> cacheList = mCache.getAll();
        for(ActivitiesFlow activitiesFlow: cacheList) {
            if(activitiesFlow.getState() == ActivitiesFlow.STATE_COMPLETED) {
                mCache.delete(activitiesFlow);
            }
        }
    }
}
