package com.amos.study.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author: amos
 * @date: 2021/4/6 12:47
 * @description:
 */
public class JavaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onTestJava();

    }

    private void onTestJava() {
        String stra = new String("hello");
        String strb = new String("hello");
        String strc =stra;
        boolean temp = strc.equals(strb);
        logMethod(temp + "");
    }

    private void logMethod(String content) {
        Log.e("JavaActivity", "打印内容:  " + content);
    }

    public static void onLaunch(Context context) {
        context.startActivity(new Intent(context, JavaActivity.class));
    }
}
