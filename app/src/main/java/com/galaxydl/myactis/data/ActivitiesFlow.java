package com.galaxydl.myactis.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import org.jetbrains.annotations.Contract;

@Entity(tableName = "activities_flow")
public final class ActivitiesFlow {
    @PrimaryKey
    private String id;

    @ColumnInfo(name = "root_id")
    private String rootId;

    private int state;

    public ActivitiesFlow(String id, String rootId) {
        this.id = id;
        this.rootId = rootId;
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
}
