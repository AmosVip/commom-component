package com.amos.study.activity;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.amos.study.base.BaseActivity;
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
       //ArrayList<? super TextView>
       //ArrayList<? extends TextView>

        Toast.makeText(this,"研究toast源码", Toast.LENGTH_SHORT).show();

    }

    private void requestPermission(){

    }

    enum  Week{

    }

    class TestView extends View{

        public TestView(Context context) {
            super(context);
        }

        public TestView(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
        }

        public TestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

        }

        @Override
        protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
            super.onLayout(changed, left, top, right, bottom);
        }
    }
}
