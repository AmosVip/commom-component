package com.amos.study.service;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @author: amos
 * @date: 2021/4/21 16:57
 * @description:
 */
public class BindService extends Service {
    private static final String TAG = BindService.class.getSimpleName();


    @Override
    public void onCreate() {
        super.onCreate();
        logMethod("onCreate");

    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        logMethod("onStart");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        logMethod("onStartCommand flags = " + flags + "  startId = " + startId);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        logMethod("onConfigurationChanged");
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        logMethod("onDestroy");
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        logMethod("onLowMemory");
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        logMethod("onTrimMemory");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        logMethod("onBind");
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        logMethod("onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        logMethod("onRebind");
        super.onRebind(intent);
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        logMethod("onTaskRemoved");
        super.onTaskRemoved(rootIntent);
    }

    private void logMethod(String content) {
        Log.e(TAG, content);
    }

    /*class MyIBinder implements IBinder{

    }*/
}
