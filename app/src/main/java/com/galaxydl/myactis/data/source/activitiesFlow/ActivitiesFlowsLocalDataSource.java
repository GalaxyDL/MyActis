package com.galaxydl.myactis.data.source.activitiesFlow;

import android.content.Context;

import com.galaxydl.myactis.data.ActivitiesFlow;
import com.galaxydl.myactis.data.dao.ActivitiesFlowDao;
import com.galaxydl.myactis.data.source.ActivityDataBase;
import com.galaxydl.myactis.util.ExecutorHolder;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.Executor;

public class ActivitiesFlowsLocalDataSource implements ActivitiesFlowDataSource {

    private static ActivitiesFlowsLocalDataSource INSTANCE;

    private final ActivitiesFlowDao mActivitiesFlowDao;

    private final Executor mExecutor;

    private ActivitiesFlowsLocalDataSource(Context context) {
        mActivitiesFlowDao = ActivityDataBase.getInstance(context.getApplicationContext()).getActivitiesFlowDao();
        mExecutor = ExecutorHolder.getExecutor();
    }

    public static ActivitiesFlowsLocalDataSource getInstance(Context context) {
        if(INSTANCE == null) {
            INSTANCE = new ActivitiesFlowsLocalDataSource(context);
        }
        return INSTANCE;
    }

    @Override
    public void listActivitiesFlows(@NotNull final ListActivitiesFlowsListener listener) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                List<ActivitiesFlow> result = mActivitiesFlowDao.listActivitiesFlow();
                if(result.isEmpty()) {
                    listener.onFailed();
                }else {
                    listener.onSuccess(result);
                }
            }
        });
    }

    @Override
    public void getActivitiesFlow(@NotNull final String id, @NotNull final GetActivitiesFlowListener listener) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                ActivitiesFlow result = mActivitiesFlowDao.getActivitiesFlowById(id);
                if(listener == null) {
                    listener.onFailed();
                }else {
                    listener.onSuccess(result);
                }
            }
        });
    }

    @Override
    public void saveActivitiesFlow(@NotNull final ActivitiesFlow activitiesFlow) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mActivitiesFlowDao.addActivityFlow(activitiesFlow);
            }
        });
    }

    @Override
    public void complete(@NotNull final ActivitiesFlow activitiesFlow) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mActivitiesFlowDao.updateActivityFlow(activitiesFlow);
            }
        });
    }

    @Override
    public void redo(@NotNull final ActivitiesFlow activitiesFlow) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mActivitiesFlowDao.updateActivityFlow(activitiesFlow);
            }
        });
    }

    @Override
    public void deleteActivitiesFlow(@NotNull final ActivitiesFlow activitiesFlow) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mActivitiesFlowDao.deleteActivityFlow(activitiesFlow);
            }
        });
    }

    @Override
    public void deleteAllCompletedActivitiesFlows() {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mActivitiesFlowDao.deleteAllCompletedActivitiesFlows();
            }
        });
    }
}
