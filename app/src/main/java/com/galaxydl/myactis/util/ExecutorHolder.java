package com.galaxydl.myactis.util;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorHolder {
    private static final int PROCESSORS_COUNT = Runtime.getRuntime().availableProcessors();

    private static final int CORE_POOL_SIZE = PROCESSORS_COUNT + 1;
    private static final int MAXIMUM_POOL_SIZE = PROCESSORS_COUNT * 2 + 1;
    private static final int KEEP_ALIVE_TIME = 5;


    private static Executor EXECUTOR;

    private ExecutorHolder() {

    }

    public static Executor getExecutor() {
        if(EXECUTOR == null) {
            EXECUTOR = new ThreadPoolExecutor(CORE_POOL_SIZE,
                    MAXIMUM_POOL_SIZE,
                    KEEP_ALIVE_TIME,
                    TimeUnit.SECONDS,
                    new LinkedBlockingDeque<Runnable>());
        }
        return EXECUTOR;
    }
}
