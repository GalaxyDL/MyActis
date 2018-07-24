package com.galaxydl.myactis.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import org.jetbrains.annotations.Contract;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Entity(tableName = "activities_flow")
public final class ActivitiesFlow implements CacheAble{

    @Ignore
    public static final int STATE_UNCOMPLETED = 1;
    @Ignore
    public static final int STATE_COMPLETED = 2;

    @PrimaryKey
    private String id;

    @ColumnInfo(name = "root_id")
    private String rootId;

    private int state;

    @Ignore
    private Map<String, Activity> flow;

    public ActivitiesFlow(String id, String rootId, int state) {
        this.id = id;
        this.rootId = rootId;
        this.state = state;
    }

    public ActivitiesFlow(String rootId) {
        this(UUID.randomUUID().toString(), rootId, STATE_UNCOMPLETED);
    }

    @Contract(pure = true)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Contract(pure = true)
    public String getRootId() {
        return rootId;
    }

    public void setRootId(String rootId) {
        this.rootId = rootId;
    }

    @Contract(pure = true)
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }


    public void setFlow(Map<String, Activity> flow) {
        this.flow = flow;
    }

    @Contract(pure = true)
    public Map<String, Activity> getFlow() {
        return flow;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivitiesFlow that = (ActivitiesFlow) o;
        return state == that.state &&
                Objects.equals(id, that.id) &&
                Objects.equals(rootId, that.rootId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, rootId, state);
    }

    @Override
    public String toString() {
        return "ActivitiesFlow{" +
                "id='" + id + '\'' +
                ", rootId='" + rootId + '\'' +
                ", state=" + state +
                ", flow=" + flow +
                '}';
    }
}
