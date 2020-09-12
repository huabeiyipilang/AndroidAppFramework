package com.penghaonan.appframework.utils.threadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ListViewThreadPool extends AThreadPool {
    public ThreadPoolExecutor createThreadPoolExecutor(BlockingQueue<Runnable> queue, RejectedExecutionHandler handler) {
        return new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS, queue, handler);
    }
}
