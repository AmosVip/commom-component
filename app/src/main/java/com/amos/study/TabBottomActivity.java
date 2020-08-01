package com.amos.study;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.google.android.material.tabs.TabLayout;

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
    }
}
