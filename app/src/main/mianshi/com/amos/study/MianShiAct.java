package com.amos.study;

import android.app.IntentService;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.SurfaceTexture;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import android.os.StatFs;
import android.util.ArrayMap;
import android.util.Log;
import android.util.LruCache;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.webkit.WebView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.core.app.JobIntentService;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amos.study.act.ServiceAct;
import com.amos.study.base.BaseActivity;
import com.amos.study.databinding.ActMianshiBinding;
import com.amos.study.utlis.MacUtils;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: amos
 * @date: 2021/4/21 16:03
 * @description: 面试
 */
public class MianShiAct extends BaseActivity implements View.OnClickListener {

    private ActMianshiBinding actBinding;

    /**
     * activity 显式跳转&隐式跳转
     *
     * @param context
     */

    public static void launch(Context context) {
        //显式跳转
        /*Intent intent = new Intent(context, MianShiAct.class);
        context.startActivity(intent);*/

        //隐式跳转
        Intent intent = new Intent("com.amos.study.MianShiAct");
        context.startActivity(intent);

        Thread.setDefaultUncaughtExceptionHandler();
        SecurityManager;
        StatFs
    }



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actBinding = ActMianshiBinding.inflate(getLayoutInflater());
        setContentView(actBinding.getRoot());
        actBinding.btnService.setOnClickListener(this);

        String mac = MacUtils.getMac(this);
        Log.e(MianShiAct.class.getSimpleName(), "mac = " + mac);


/*StringBuilder;
StringBuffer;
String;
TreeMap;
LruCache;
ArrayMap;
HashMap;
SimpleAdapter;
ListView;
RecyclerView;
        ConcurrentHashMap;
        RecyclerView;
        JobService;
        JobIntentService;
        LocalBroadcastManager.getInstance();
        getContentResolver();
        ArrayList;
        WebView;
        Surface;


        SurfaceView;
        TextureView;
        VideoView;
        HashMap<String,Integer> hashMap = new HashMap();
        hashMap.put("1",1);
        TreeMap;
        LinkedTreeMap;
        LinkedHashMap;

        Handler;
        Looper;
        Message;
        MessageQueue;
        HandlerThread;
        IntentService;
        JobService;
        ThreadPoolExecutor;
        Executors.newFixedThreadPool();



        AtomicInteger;
        synchronized ()*/
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btnService) {
            ServiceAct.launch(this);
        }
    }
}
