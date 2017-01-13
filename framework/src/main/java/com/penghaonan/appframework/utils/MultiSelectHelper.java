package com.penghaonan.appframework.utils;

import android.support.annotation.NonNull;
import android.util.SparseBooleanArray;

import java.util.Collection;

public class MultiSelectHelper {
    private SparseBooleanArray checkedArray;
    private SelectChangedListener listener;

    public interface SelectChangedListener {
        void onSelectChanged();
    }

    public MultiSelectHelper(SelectChangedListener listener) {
        this.listener = listener;
        checkedArray = new SparseBooleanArray();
    }

    public void setChecked(int position, boolean checked) {
        if (checked) {
            if (!contains(position)) {
                checkedArray.put(position, true);
                notifySelectChanged();
            }
        } else {
            if (contains(position)) {
                checkedArray.delete(position);
                notifySelectChanged();
            }
        }
    }

    private void notifySelectChanged() {
        if (listener != null) {
            listener.onSelectChanged();
        }
    }

    private boolean contains(int position) {
        return checkedArray.indexOfKey(position) >= 0;
    }

    public boolean toggleChecked(int position) {
        boolean toChecked = !isChecked(position);
        setChecked(position, toChecked);
        return toChecked;
    }

    public boolean isChecked(int position) {
        return checkedArray.get(position, false);
    }

    public int checkedSize() {
        return checkedArray.size();
    }

    public void iterator(Collection data, @NonNull Iterator iterator) {

        java.util.Iterator iterator1 = data.iterator();
        int i = 0;
        while (iterator1.hasNext()) {
            if (isChecked(i)) {
                iterator.onNext(iterator1.next());
            }
        }
    }

    public void clear() {
        checkedArray.clear();
    }

    public interface Iterator<T> {
        void onNext(T t);
    }
}
