package com.common.ui.flow;

import android.view.View;

import androidx.annotation.Nullable;

import java.util.List;

/**
 * <pre>
 *     author : amos
 *     time   : 2020/08/02 14:14
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public interface ICFlow<D> {

    CFlowLayout setData(@Nullable List<D> dataList);

    CFlowLayout setCallBack(@Nullable OnCallBack<D> callBack);


    interface OnCallBack<D> {

        View getChildView(D data, int position);

        void onClick(View childView, D data, int position);
    }
}
