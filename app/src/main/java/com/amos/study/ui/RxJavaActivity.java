package com.amos.study.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.amos.study.BaseActivity;
import com.amos.study.R;
import com.bumptech.glide.Glide;

import java.util.Collections;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * @author: amos
 * @date: 2021/2/4 13:58
 * @description: RxJava的学习
 */
public class RxJavaActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
        TextView tvTest = findViewById(R.id.tvTest);
        //tvTest.addOnLayoutChangeListener();
        /*ViewPager2 vp = new ViewPager2(this);
        vp.registerOnPageChangeCallback(bew);
        RecyclerView rv = new RecyclerView(this);
        rv.addItemDecoration();*/
        //Collections.unmodifiableList()
       /* Glide.with(this).load()
                .skipMemoryCache()
                .diskCacheStrategy().into()*/
        studyRxJavaMethod();
    }

    /**
     * 被观察者（Observable）	产生事件	顾客
     * 观察者（Observer）	接收事件，并给出响应动作	厨房
     * 订阅（Subscribe）	连接 被观察者 & 观察者	服务员
     * 事件（Event）	被观察者 & 观察者 沟通的载体	菜式
     * <p>
     * 作者：Carson_Ho
     * 链接：https://www.jianshu.com/p/a406b94f3188
     * 来源：简书
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    private void studyRxJavaMethod() {
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                logMethod("subscribe = " + Thread.currentThread().getName() + Thread.currentThread().getId());
                emitter.onNext("测试数据");
                emitter.onComplete();
            }
        });
        observable.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                logMethod("onSubscribe = " + Thread.currentThread().getName() + Thread.currentThread().getId());
            }

            @Override
            public void onNext(@NonNull String s) {
                logMethod("content = " + s + "  onNext = " + Thread.currentThread().getName() + Thread.currentThread().getId());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                logMethod("onError = " + Thread.currentThread().getName() + Thread.currentThread().getId());
            }

            @Override
            public void onComplete() {
                logMethod("onComplete = " + Thread.currentThread().getName() + Thread.currentThread().getId());
            }
        });

    }


    private void logMethod(String logContent) {
        Log.e(getClass().getSimpleName(), logContent);
    }

    public static void launch(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, RxJavaActivity.class);
        context.startActivity(intent);
    }
}
