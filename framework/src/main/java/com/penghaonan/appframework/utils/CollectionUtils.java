package com.penghaonan.appframework.utils;

import java.util.Collection;

public class CollectionUtils {

    public static boolean isEmpty(Object[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.size() == 0;
    }

    public static boolean notEmpty(Collection collection) {
        return !isEmpty(collection);
    }

    public static boolean notEmpty(Object[] array) {
        return !isEmpty(array);
    }

    public static int size(Collection collection) {
        return collection == null ? 0 : collection.size();
    }

    /**
     * 避免使用Collection.addAll()的时候，参数为null的问题
     */
    public static <T> void addAll(Collection<T> parent, Collection<T> child) {
        if (parent == null || child == null) {
            return;
        }
        parent.addAll(child);
    }
}
