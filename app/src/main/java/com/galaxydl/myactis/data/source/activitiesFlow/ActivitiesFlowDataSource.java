package com.galaxydl.myactis.data.source.activitiesFlow;

import com.galaxydl.myactis.data.ActivitiesFlow;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface ActivitiesFlowDataSource {

    interface ListActivitiesFlowsListener {

        void onSuccess(List<ActivitiesFlow> activitiesFlows);

        void onFailed();
    }

    interface GetActivitiesFlowListener {

        void onSuccess(ActivitiesFlow activitiesFlow);

        void onFailed();
    }

    void listActivitiesFlows(@NotNull ListActivitiesFlowsListener listener);

    void getActivitiesFlow(@NotNull String id, @NotNull GetActivitiesFlowListener listener);

    void saveActivitiesFlow(@NotNull ActivitiesFlow activitiesFlow);

    void complete(@NotNull ActivitiesFlow activitiesFlow);

    void redo(@NotNull ActivitiesFlow activitiesFlow);

    void deleteActivitiesFlow(@NotNull ActivitiesFlow activitiesFlow);

    void deleteAllCompletedActivitiesFlows();
}
