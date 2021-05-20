package com.amos.study.act;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

import androidx.annotation.Nullable;

import com.amos.study.R;
import com.amos.study.base.BaseActivity;
import com.amos.study.databinding.ActServiceBinding;
import com.amos.study.service.BindService;
import com.amos.study.service.StartService;

import java.util.EventListener;

/**
 * @author: amos
 * @date: 2021/4/21 16:02
 * @description: Service的使用
 */
public class ServiceAct extends BaseActivity implements View.OnClickListener {
    private ActServiceBinding actBinding;

    public static void launch(Context context) {
        //显示跳转
        /*Intent intent = new Intent(context, ServiceAct.class);
        context.startActivity(intent);*/

        //隐式跳转
        Intent intent = new Intent("com.amos.study.ServiceAct");
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actBinding = ActServiceBinding.inflate(getLayoutInflater());
        setContentView(actBinding.getRoot());

        actBinding.btnStartService.setOnClickListener(this);
        actBinding.btnStopService.setOnClickListener(this);

        actBinding.btnBindService.setOnClickListener(this);
        actBinding.btnUnBindService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btnStartService) {
            startService();
        } else if (id == R.id.btnStopService) {
            stopService();
        } else if (id == R.id.btnBindService) {
            bindService();
        } else if (id == R.id.btnUnBindService) {
            unBindService();
        }
    }

    private void startService() {
        Intent intent = new Intent(this, StartService.class);
        startService(intent);
        //startForegroundService()
    }

    private void stopService() {
        Intent intent = new Intent(this, StartService.class);
        stopService(intent);
    }


    private void bindService() {
        Intent intent = new Intent(this, BindService.class);
        bindService(intent,conn,Context.BIND_AUTO_CREATE);
    }

    private void unBindService() {

    }

    private static ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
}
