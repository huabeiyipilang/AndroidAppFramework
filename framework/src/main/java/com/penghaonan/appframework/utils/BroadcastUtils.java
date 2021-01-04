package com.penghaonan.appframework.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.penghaonan.appframework.AppDelegate;

import java.lang.ref.SoftReference;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class BroadcastUtils {
    private static final List<ReceiverWrapper> receiverWrapperList = new LinkedList<>();

    public static void send(Intent intent) {
        LocalBroadcastManager.getInstance(AppDelegate.getApp()).sendBroadcast(intent);
    }

    public static void sendAction(String action) {
        Intent intent = new Intent(action);
        send(intent);
    }

    public static void register(BroadcastReceiver receiver, IntentFilter filter) {
        ReceiverWrapper wrapper = new ReceiverWrapper(filter, receiver);
        LocalBroadcastManager.getInstance(AppDelegate.getApp()).registerReceiver(wrapper, filter);
        AppDelegate.getApp().registerReceiver(wrapper, filter);
        receiverWrapperList.add(wrapper);
        clearRelease();
    }

    private static void clearRelease() {
        Logger.i("clearRelease before:" + receiverWrapperList.size());
        Iterator<ReceiverWrapper> iterator = receiverWrapperList.iterator();
        while (iterator.hasNext()) {
            ReceiverWrapper wrapper = iterator.next();
            if (wrapper.receiverRef == null || wrapper.receiverRef.get() == null) {
                unregister(wrapper);
                iterator.remove();
            }
        }
        Logger.i("clearRelease after:" + receiverWrapperList.size());
    }

    public static void register(BroadcastReceiver receiver, String[] actions) {
        if (CollectionUtils.isEmpty(actions)) {
            return;
        }
        IntentFilter intentFilter = new IntentFilter();
        for (String action : actions) {
            intentFilter.addAction(action);
        }
        register(receiver, intentFilter);
    }

    public static void unregister(BroadcastReceiver receiver) {
        Iterator<ReceiverWrapper> iterator = receiverWrapperList.iterator();
        while (iterator.hasNext()) {
            ReceiverWrapper wrapper = iterator.next();
            if (wrapper.getInnerReceiver() == receiver) {
                try {
                    LocalBroadcastManager.getInstance(AppDelegate.getApp()).unregisterReceiver(wrapper);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    AppDelegate.getApp().unregisterReceiver(wrapper);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                iterator.remove();
            }
        }
    }

    public static abstract class ActionReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                return;
            }
            String action = intent.getAction();
            if (TextUtils.isEmpty(action)) {
                return;
            }
            onActionReceive(action, intent);
        }

        protected abstract void onActionReceive(String action, Intent intent);
    }

    private abstract static class Receiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                return;
            }
            String action = intent.getAction();
            if (TextUtils.isEmpty(action)) {
                return;
            }
            onReceive(context, action, intent);
        }

        abstract void onReceive(Context context, @NonNull String action, @NonNull Intent intent);

    }

    private static class ReceiverWrapper extends BroadcastReceiver {

        IntentFilter filter;
        SoftReference<BroadcastReceiver> receiverRef;

        ReceiverWrapper(IntentFilter filter, BroadcastReceiver receiver) {
            this.filter = filter;
            this.receiverRef = new SoftReference<>(receiver);
        }

        BroadcastReceiver getInnerReceiver() {
            return receiverRef.get();
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            BroadcastReceiver receiver = receiverRef.get();
            if (receiver != null) {
                receiver.onReceive(context, intent);
            } else {
                StringBuilder sb = new StringBuilder();
                Iterator<String> iterator = filter.actionsIterator();
                while (iterator.hasNext()) {
                    sb.append(" ").append(iterator.next());
                }
                Logger.e("broadcast dispatch failed:" + sb.toString());
            }
        }
    }
}
