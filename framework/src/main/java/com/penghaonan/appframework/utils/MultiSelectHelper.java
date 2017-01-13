package com.penghaonan.appframework.utils;

import android.support.annotation.NonNull;
import android.util.SparseBooleanArray;

import java.util.Collection;

public class MultiSelectHelper {
    private SparseBooleanArray checkedArray;

    public MultiSelectHelper() {
        checkedArray = new SparseBooleanArray();
    }

    public void setChecked(int position, boolean checked) {
        checkedArray.put(position, checked);
    }

    public boolean toggleChecked(int position) {
        boolean toChecked = !isChecked(position);
        setChecked(position, toChecked);
        return toChecked;
    }

    public boolean isChecked(int position) {
        return checkedArray.get(position, false);
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
