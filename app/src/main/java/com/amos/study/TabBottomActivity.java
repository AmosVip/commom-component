package com.amos.study;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     author : amos
 *     time   : 2020/08/01 10:02
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class TabBottomActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TabLayout tabLayout = new TabLayout(this);
        //tabLayout.newTab().setCustomView()
       //ArrayList<? super TextView>
       //ArrayList<? extends TextView>

        Toast.makeText(this,"研究toast源码", Toast.LENGTH_SHORT).show();

    }

    private void requestPermission(){

    }
}
