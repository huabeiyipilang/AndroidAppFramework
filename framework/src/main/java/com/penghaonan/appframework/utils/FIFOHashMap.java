package com.penghaonan.appframework.utils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class FIFOHashMap<K, V> {

    private Map<K, V> map = new HashMap<>();
    private LinkedList<K> kList = new LinkedList<>();

    public synchronized void push(K k, V v) {
        if (kList.contains(k)) {
            kList.remove(k);
        }
        kList.push(k);
        map.put(k, v);
    }

    public synchronized Map.Entry<K, V> poll() {
        K key = kList.poll();
        if (key != null) {
            V value = map.remove(key);
            return new FIFOEntry(key, value);
        }
        return null;
    }

    public synchronized Map.Entry<K, V> pollLast() {
        K key = kList.pollLast();
        if (key != null) {
            V value = map.remove(key);
            return new FIFOEntry(key, value);
        }
        return null;
    }

    public synchronized Map.Entry<K, V> peek() {
        K key = kList.peek();
        if (key == null) {
            return null;
        } else {
            return new FIFOEntry(key, map.get(key));
        }
    }

    public synchronized void remove(K k) {
        if (kList.contains(k)) {
            kList.remove(k);
            map.remove(k);
        }
    }

    public synchronized boolean containsKey(Object key) {
        return kList.contains(key);
    }

    public int size() {
        return kList.size();
    }

    private class FIFOEntry implements Map.Entry<K, V> {
        final K k;
        V v;

        FIFOEntry(K k, V v) {
            this.k = k;
            this.v = v;
        }

        @Override
        public K getKey() {
            return k;
        }

        @Override
        public V getValue() {
            return v;
        }

        @Override
        public V setValue(V value) {
            return v = value;
        }
    }
}
