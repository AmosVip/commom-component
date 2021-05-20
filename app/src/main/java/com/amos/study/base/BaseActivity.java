package com.amos.study.base;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.PreferenceActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.NestedScrollingChild3;
import androidx.lifecycle.Lifecycle;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     author : amos
 *     time   : 2020/07/30 18:23
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public abstract class BaseActivity extends AppCompatActivity {
    String TAG = getClass().getName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logLifecycle("onCreate");
        ArrayList<String> arrayList = new ArrayList<>();
    }

    @Override
    protected void onStart() {
        super.onStart();
        logLifecycle("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        logLifecycle("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        logLifecycle("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        logLifecycle("onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        logLifecycle("onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        logLifecycle("onDestroy");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        logLifecycle("onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        logLifecycle("onRestoreInstanceState");
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        logLifecycle("onAttachedToWindow");
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        logLifecycle("onDetachedFromWindow");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        logLifecycle("onNewIntent");
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        logLifecycle("onWindowFocusChanged    hasFocus =  " + hasFocus);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        logLifecycle("onConfigurationChanged");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        logLifecycle("onActivityResult:  requestCode= " + requestCode + "  resultCode=" + resultCode);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }



    protected void logLifecycle(String logStr) {
        Log.d(TAG+ "生命周期" , "=====  " + logStr + "  =====");
    }
}
