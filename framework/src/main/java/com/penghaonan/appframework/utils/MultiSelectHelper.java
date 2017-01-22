package com.penghaonan.appframework.utils;

import android.support.annotation.NonNull;
import android.util.SparseBooleanArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MultiSelectHelper<T> {
    private final List<Integer> checkedArray = new LinkedList<>();
    private SelectChangedListener listener;

    public interface SelectChangedListener {
        void onSelectChanged();
    }

    public MultiSelectHelper(SelectChangedListener listener) {
        this.listener = listener;
    }

    public void setChecked(Integer position, boolean checked) {
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
            boolean notifyChanged = !isAllChecked(count);
            checkedArray.clear();
            for (int i = 0; i < count; i++) {
                checkedArray.add(i);
            }
            if (notifyChanged) {
                notifySelectChanged();
            }
        }
    }

    public boolean isAllChecked(int count) {
        return count > 0 && checkedArray.size() == count;
    }

    public boolean isAllUnchecked() {
        return checkedArray.size() == 0;
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

    public boolean isChecked(Integer position) {
        synchronized (checkedArray) {
            return checkedArray.contains(position);
        }
    }

    public int checkedSize() {
        synchronized (checkedArray) {
            return checkedArray.size();
        }
    }

    public void fillSelectedData(@NonNull Collection<T> data, @NonNull Collection<T> outCollection) {
        synchronized (checkedArray) {
            Iterator<T> iterator = data.iterator();
            int pos = 0;
            while (iterator.hasNext()) {
                T t = iterator.next();
                if (isChecked(pos)) {
                    outCollection.add(t);
                }
                pos++;
            }
        }
    }

    public List<T> getSelectedData(@NonNull Collection<T> data) {
        List<T> res = new ArrayList<>();
        fillSelectedData(data, res);
        return res;
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
}
