package com.penghaonan.appframework.utils.threadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public abstract class AThreadPool {
    private ThreadPoolExecutor executor;
    private RejectedExecutionHandler handler;
    private BlockingQueue<Runnable> taskQueue;

    public abstract ThreadPoolExecutor createThreadPoolExecutor(BlockingQueue<Runnable> blockingQueue, RejectedExecutionHandler rejectedExecutionHandler);

    private ThreadPoolExecutor getExecutor() {
        if (this.executor == null) {
            LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
            this.taskQueue = linkedBlockingQueue;
            this.executor = createThreadPoolExecutor(linkedBlockingQueue, new RejectedExecutionHandler() {
                public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                }
            });
        }
        return this.executor;
    }

    public void stop(boolean now) {
        ThreadPoolExecutor threadPoolExecutor = this.executor;
        if (threadPoolExecutor == null) {
            return;
        }
        if (now) {
            threadPoolExecutor.shutdownNow();
        } else {
            threadPoolExecutor.shutdown();
        }
    }

    public void addTask(ThreadPoolTask task) {
        if (!checkDuplicate(task)) {
            getExecutor().execute(task);
        }
    }

    private ThreadPoolTask findTaskByKey(Object key) {
        for (Runnable runnable : this.taskQueue) {
            if ((runnable instanceof ThreadPoolTask) && ((ThreadPoolTask) runnable).getKey() == key) {
                return (ThreadPoolTask) runnable;
            }
        }
        return null;
    }

    public void cancel(Object key) {
        ThreadPoolTask task = findTaskByKey(key);
        if (task != null) {
            task.cancel();
            this.taskQueue.remove(task);
        }
    }

    public void cancel(ThreadPoolTask task) {
        task.cancel();
        this.taskQueue.remove(task);
    }

    private boolean checkDuplicate(ThreadPoolTask task) {
        if (this.taskQueue == null || findTaskByKey(task) == null) {
            return false;
        }
        return true;
    }
}
