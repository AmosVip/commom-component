package com.amos.study.socket;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.amos.study.base.BaseActivity;
import com.amos.study.R;

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
