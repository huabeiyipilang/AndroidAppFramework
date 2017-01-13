package com.penghaonan.appframework.utils;

import java.util.Collection;

public class CollectionUtils {
    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.size() == 0;
    }

    public static int size(Collection collection) {
        return collection == null ? 0 : collection.size();
    }
}
