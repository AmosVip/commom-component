package com.amos.study.socket;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.amos.study.BaseActivity;
import com.amos.study.R;
import com.google.android.material.tabs.TabLayout;

/**
 * @author: amos
 * @date: 2020/12/1 10:33
 * @description:
 */
public class SocketActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket);
        //TabLayout
        //DialogFragment
    }



    public static void launch(Context context){
        Intent intent = new Intent(context,SocketActivity.class);
        context.startActivity(intent);
    }
}
