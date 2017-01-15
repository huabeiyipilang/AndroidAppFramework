package com.penghaonan.appframework.utils;

import android.support.annotation.NonNull;
import android.util.SparseBooleanArray;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class MultiSelectHelper {
    private final List<Integer> checkedArray = new LinkedList<>();
    private SelectChangedListener listener;

    public interface SelectChangedListener {
        void onSelectChanged();
    }

    public MultiSelectHelper(SelectChangedListener listener) {
        this.listener = listener;
    }

    public void setChecked(int position, boolean checked) {
        synchronized (checkedArray) {
            if (checked) {
                if (!checkedArray.contains(position)) {
                    checkedArray.add(position);
                    notifySelectChanged();
                }
            } else {
                if (checkedArray.contains(position)) {
                    checkedArray.remove(position);
                    notifySelectChanged();
                }
            }
        }
    }

    public void checkAll(int count) {
        synchronized (checkedArray) {
            boolean notifyChanged = checkedArray.size() == count;
            for (int i = 0; i < count; i++) {
                checkedArray.add(i);
            }
            if (notifyChanged) {
                notifySelectChanged();
            }
        }
    }

    private void notifySelectChanged() {
        if (listener != null) {
            listener.onSelectChanged();
        }
    }

    public boolean toggleChecked(int position) {
        synchronized (checkedArray) {
            boolean toChecked = !isChecked(position);
            setChecked(position, toChecked);
            return toChecked;
        }
    }

    public boolean isChecked(int position) {
        synchronized (checkedArray) {
            return checkedArray.contains(position);
        }
    }

    public int checkedSize() {
        synchronized (checkedArray) {
            return checkedArray.size();
        }
    }

    public void iterator(@NonNull Collection data, @NonNull Iterator iterator, boolean sort) {
        synchronized (checkedArray) {
            if (sort) {
                Collections.sort(checkedArray);
            }
            Object[] array = data.toArray();
            for (Integer pos : checkedArray) {
                iterator.onNext(array[pos]);
            }
        }
    }

    public void clear() {
        synchronized (checkedArray) {
            boolean notifyChanged = false;
            if (checkedArray.size() > 0) {
                notifyChanged = true;
            }
            checkedArray.clear();
            if (notifyChanged) {
                notifySelectChanged();
            }
        }
    }

    public interface Iterator<T> {
        void onNext(T t);
    }
}
