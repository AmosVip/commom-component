package com.amos.study.utlis;

/**
 * @author: amos
 * @date: 2020/4/1 10:41
 * @description: 防止快速点点击
 */
public class PreventFastClickHelper {
    private static long mLastClickDuration = 0;

    //判断是否是快速点击
    public static boolean isFastClick() {
        if ((System.currentTimeMillis() - mLastClickDuration) <= 600) {
            mLastClickDuration = System.currentTimeMillis();
            return true;
        }
        mLastClickDuration = System.currentTimeMillis();
        return false;
    }
}
