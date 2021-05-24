package com.penghaonan.appframework.utils;

import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class WeakObservableList<T> {
    private final List<CustomWeakReference> observerList = new LinkedList<>();

    /**
     * 注册监听
     *
     * @param listener
     */
    public void registerListener(T listener) {
        iterate(new OnIterator<T>() {
            private boolean needAdd = true;

            @Override
            protected boolean onIterator(T item) {
                if (listener == item) {
                    needAdd = false;
                    return true;
                }
                return false;
            }

            @Override
            protected void onFinish() {
                if (needAdd) {
                    observerList.add(new CustomWeakReference(listener));
                }
            }
        });
    }

    /**
     * 移除监听
     *
     * @param listener
     */
    public void unregisterListener(T listener) {
        iterate(new OnIterator<T>() {
            @Override
            protected boolean onIterator(T item) {
                if (listener == item) {
                    remove();
                    return true;
                }
                return false;
            }
        });
    }

    /**
     * 遍历回调
     */
    public static abstract class OnIterator<T> {
        Iterator<CustomWeakReference> iterator;

        protected void onStart() {
        }

        /**
         * return true 终止循环
         */
        protected abstract boolean onIterator(T item);

        protected void onFinish() {
        }

        protected void remove() {
            iterator.remove();
        }
    }

    /**
     * 遍历器，过滤被回收的部分
     */
    public void iterate(OnIterator onIterator) {
        Iterator<CustomWeakReference> iterator = observerList.iterator();
        onIterator.iterator = iterator;
        onIterator.onStart();
        while (iterator.hasNext()) {
            CustomWeakReference reference = iterator.next();
            T keptObj = (T) reference.get();
            if (reference.get() == null || keptObj == null) {
                iterator.remove();
            } else {
                onIterator.onIterator(keptObj);
            }
        }
        onIterator.onFinish();
    }

    private static class CustomWeakReference<T> extends WeakReference {

        public CustomWeakReference(T referent) {
            super(referent);
        }

        @Override
        public T get() {
            return (T) super.get();
        }
    }
}
