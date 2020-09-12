package com.penghaonan.appframework.utils.threadpool;

public abstract class ThreadPoolTask implements Runnable {
    private boolean cancelled;
    private Object key;

    protected abstract void doInBackground();

    public void setKey(Object keyObj) {
        this.key = keyObj;
    }

    public Object getKey() {
        Object obj = this.key;
        return obj == null ? this : obj;
    }

    public void cancel() {
        this.cancelled = true;
    }

    /* access modifiers changed from: protected */
    public boolean isCancelled() {
        return this.cancelled;
    }

    public final void run() {
        doInBackground();
    }
}
