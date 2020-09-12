package com.penghaonan.appframework.utils.threadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class IOThreadPool extends AThreadPool {
    public ThreadPoolExecutor createThreadPoolExecutor(BlockingQueue<Runnable> queue, RejectedExecutionHandler handler) {
        return new ThreadPoolExecutor(3, 10, 10, TimeUnit.SECONDS, queue, handler);
    }
}
