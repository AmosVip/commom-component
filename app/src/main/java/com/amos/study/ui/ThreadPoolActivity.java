package com.amos.study.ui;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.amos.study.base.BaseActivity;
import com.amos.study.R;

/**
 * @author: amos
 * @date: 2020/10/26 15:26
 * @description:
 */
public class ThreadPoolActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_pool);

        /*ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor();

        HandlerThread;
        Process.myPid();*/
    }

    //==========================================thread start ==================================================

    /**
     * 线程的学习
     */
    private void studyThread() {


    }

    class AsyncTaskStudy extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... strings) {
            return null;
        }


    }


    //==========================================thread end ==================================================

    //==========================================thread pool start ==================================================

    /**
     * Android 中最常见线程池有四种，分别是：
     * FixedThreadPool、
     * CacheThreadPool、
     * ScheduledThreadPool
     * 以及 SingleThreadPool，
     * 它们都直接或间接的通过配置 ThreadPoolExecutor 来实现自己的功能特性
     */
    private void studyFixThreadPool() {
        //Executors.newFixedThreadPool()
        //TabLayout
    }

    private void useThreadPool() {
        //ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor()

    }


    //==========================================thread pool end ==================================================

    public static void launch(Context context) {
        context.startActivity(new Intent(context, ThreadPoolActivity.class));
    }
}
