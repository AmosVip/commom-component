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
import android.preference.PreferenceManager;
import android.util.ArrayMap;
import android.util.Log;
import android.util.LongSparseArray;
import android.util.LruCache;
import android.util.SparseLongArray;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import android.widget.ViewFlipper;

import androidx.annotation.Nullable;
import androidx.core.app.JobIntentService;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amos.study.act.ServiceAct;
import com.amos.study.annotation.MyAnnotation;
import com.amos.study.base.BaseActivity;
import com.amos.study.databinding.ActMianshiBinding;
import com.amos.study.utlis.MacUtils;
import com.bumptech.glide.Glide;
import com.common.ui.UIFlowLayout;
import com.google.gson.internal.LinkedTreeMap;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

import okhttp3.OkHttp;
import okhttp3.OkHttpClient;

/**
 * @author: amos
 * @date: 2021/4/21 16:03
 * @description: 面试
 */
public class MianShiAct extends BaseActivity implements View.OnClickListener {

    @MyAnnotation
    private ActMianshiBinding actBinding;

    FlowAdapter mFlowAdapter;

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
    }

    //private volatile
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actBinding = ActMianshiBinding.inflate(getLayoutInflater());
        setContentView(actBinding.getRoot());
        actBinding.btnService.setOnClickListener(this);

        actBinding.uiFlowLayout.setUIFlowAdapter(mFlowAdapter = new FlowAdapter());


        /*String mac = MacUtils.getMac(this);
        Log.e(MianShiAct.class.getSimpleName(), "mac = " + mac);
        bubbleSort();
        String temp1 = getMaxHuiWenStr("abamnnm");
        logE("回文字符串: " + temp1);
        String temp2 = getMaxHuiWenStr("aacdefg");
        logE("回文字符串: " + temp2);*/
        //HttpURLConnection;


        //startActivitie(intent);
        //Class.forName("").newInstance()
        //Handler
        //Glide.with().load().into()
     /*   Method;
        Class;
       // AbstractProcessor
        HashMap
        LinkedHashMap;
        TreeMap;
        LruCache;
        RecyclerView;
        LinearLayoutManager*/

/*AtomicInteger;
Object;
LruCache;
HashMap;
ArrayList;
        Proxy;
        JobIntentService;
        LocalBroadcastManager;*/


/*
 Thread.setDefaultUncaughtExceptionHandler();
        SecurityManager;
        StatFs
StringBuilder;
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

    private int[] dataAry = new int[]{1, 7, 3, 2, 77, 22, 434, 2222, 55, 4444, 222, 99};

    private void bubbleSort() {
        /*printSortData("冒泡排序前");
        for (int i = 0, count = dataAry.length; i < count; i++) {
            for (int j = 0; j < count - 1 - i; j++) {
                if (dataAry[j] > dataAry[j + 1]) {
                    int temp = dataAry[j];
                    dataAry[j] = dataAry[j + 1];
                    dataAry[j + 1] = temp;
                }
            }
            logE("冒泡 = " + i);
        }
        printSortData("冒泡排序后");*/

        bubbleSort2();
//        bubbleSort3();

        selectSort();
        printSortData("快速排序前", quickAry);
        quickSort(quickAry, 0, quickAry.length - 1);
        printSortData("快速排序前", quickAry);

        SparseLongArray
                LongSparseArray;
    }

    private void bubbleSort2() {
        printSortData("冒泡2排序前", dataAry);
        for (int i = 0, count = dataAry.length; i < count; i++) {
            boolean isSorted = true;
            for (int j = 0; j < count - 1 - i; j++) {
                if (dataAry[j] > dataAry[j + 1]) {
                    int temp = dataAry[j];
                    dataAry[j] = dataAry[j + 1];
                    dataAry[j + 1] = temp;
                    isSorted = false;
                }
            }
            if (isSorted) {
                break;
            }
            logE("冒泡2 = " + i);
        }
        printSortData("冒泡2排序后", dataAry);
    }

    private void bubbleSort3() {
        printSortData("冒泡3排序前", dataAry);
        //记录最后一次交换的位置
        int lastExchangeIndex = 0;
        //无序数列的边界，每次比较只需要比到这里为止
        int sortBorder = dataAry.length - 1;
        for (int i = 0, count = dataAry.length; i < count; i++) {
            //有序标记，每一轮的初始是true
            boolean isSorted = true;
            for (int j = 0; j < sortBorder; j++) {
                if (dataAry[j] > dataAry[j + 1]) {
                    int temp = dataAry[j];
                    dataAry[j] = dataAry[j + 1];
                    dataAry[j + 1] = temp;
                    //有元素交换，所以不是有序，标记变为false
                    isSorted = false;
                    //把无序数列的边界更新为最后一次交换元素的位置
                    lastExchangeIndex = j;
                }
            }
            sortBorder = lastExchangeIndex;
            if (isSorted) {
                break;
            }
            logE("冒泡3 = " + i);
        }
        printSortData("冒泡3排序后", dataAry);
    }

    private int[] selAry = new int[]{1, 7, 3, 2, 77, 22, 434, 2222, 55, 4444, 222, 99};

    private void selectSort() {
        printSortData("选择排序前", selAry);
        for (int i = 0, count = selAry.length; i < count - 1; i++) {
            for (int j = i + 1; j < count; j++) {
                if (selAry[i] > selAry[j]) {
                    int temp = selAry[j];
                    selAry[j] = selAry[i];
                    selAry[i] = temp;
                }

            }
            logE("选择排序 = " + i);
        }
        printSortData("选择排序后", selAry);
    }

    private int[] quickAry = new int[]{1, 7, 3, 2, 77, 22, 434, 2222, 55, 4444, 222, 99};

    private void quickSort(int arr[], int start, int end) {
        if (start >= end)
            return;
        int i = start;
        int j = end;
        // 基准数
        int baseval = arr[start];
        while (i < j) {
            // 从右向左找比基准数小的数
            while (i < j && arr[j] >= baseval) {
                j--;
            }
            if (i < j) {
                arr[i] = arr[j];
                i++;
            }
            // 从左向右找比基准数大的数
            while (i < j && arr[i] < baseval) {
                i++;
            }
            if (i < j) {
                arr[j] = arr[i];
                j--;
            }
        }
        // 把基准数放到i的位置
        arr[i] = baseval;
        // 递归
        quickSort(arr, start, i - 1);
        quickSort(arr, i + 1, end);
    }


    private int[] insertAry = new int[]{1, 7, 3, 2, 77, 22, 434, 2222, 55, 4444, 222, 99};

    // 插入排序
    private void insertSort(int arr[], int length) {
        for (int i = 1; i < length; i++) {
            int j;
            if (arr[i] < arr[i - 1]) {
                int temp = arr[i];
                for (j = i - 1; j >= 0 && temp < arr[j]; j--) {
                    arr[j + 1] = arr[j];
                }
                arr[j + 1] = temp;
            }
        }
    }

    /**
     * 找出字符串中最大的回文字符串
     *
     * @param str 传入的字符串
     * @return
     */
    private String getMaxHuiWenStr(String str) {
        int length = str.length();
        String maxString = "";
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j <= length; j++) {
                String tempStr = str.substring(i, j);
                if (isHuiWenStr(tempStr) && tempStr.length() > maxString.length()) {
                    maxString = tempStr;
                }
            }
        }
        return maxString;
    }

    private boolean isHuiWenStr(String str) {
        if (str.length() == 1) {
            return false;
        }
        boolean flag = true;
        int length = str.length();
        char[] tempChar = str.toCharArray();
        for (int i = 0, j = length - 1; i <= j; i++, j--) {
            if (tempChar[i] != tempChar[j]) {
                flag = false;
            }
        }
        return flag;
    }


    private void printSortData(String tips, int[] ary) {
        logE(tips + Arrays.toString(ary));
    }

    private void logE(String content) {
        Log.e("MianShiAct", content);
    }

    class FlowAdapter extends UIFlowLayout.UIFlowAdapter {
        public int count = 30;

        public void setCount(int count) {
            this.count = count;
        }

        @Override
        public int getSize() {
            return count;
        }

        @NotNull
        @Override
        public View getView(@NotNull Context context, int position) {
            TextView view = new TextView(context);
            view.setText("测试 = " + position);
            view.setPadding(20, 20, 20, 20);
            view.setBackgroundResource(R.color.color_home_red);
            return view;
        }

        @Override
        public void onLabelViewClick(@NotNull View view, int position) {
            Toast.makeText(MianShiAct.this, "position = " + position, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btnService) {
            //ServiceAct.launch(this);
            if (mFlowAdapter != null) {
                mFlowAdapter.setCount(10);
                mFlowAdapter.notifyDataSetChanged();
            }
        }
    }
}
