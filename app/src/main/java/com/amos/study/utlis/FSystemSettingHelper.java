package com.amos.study.utlis;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import androidx.core.app.NotificationManagerCompat;


/**
 * @author: amos
 * @date: 2020/11/25 14:29
 * @description: 系统设置 帮助类
 */
public class FSystemSettingHelper {
    private static boolean isHasLaunchToNotificationSetting = false;

    /**
     * 是否有开启通知
     *
     * @return
     */
    public static boolean isHasOpenNotification() {
        //return NotificationManagerCompat.from(AppApplication.getInstance()).areNotificationsEnabled();
        return false;
    }

    /**
     * 进入通知设置界面
     *
     * @param act
     */
    public static void openNotificationSetting(Activity act) {
        try {
            setIsHasLaunchToNotificationSetting(true);
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
                intent.putExtra(Settings.EXTRA_APP_PACKAGE, act.getPackageName());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                act.startActivity(intent);
            } else if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Intent intent = new Intent();
                intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
                intent.putExtra("app_package", act.getPackageName());
                intent.putExtra("app_uid", act.getApplicationInfo().uid);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                act.startActivity(intent);
            } else if (android.os.Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.setData(Uri.parse("package:" + act.getPackageName()));
                act.startActivity(intent);
            }
        } catch (Exception e) {

        }
    }

    public static void setIsHasLaunchToNotificationSetting(boolean isHasLaunchToNotificationSetting) {
        FSystemSettingHelper.isHasLaunchToNotificationSetting = isHasLaunchToNotificationSetting;
    }

    public static boolean isIsHasLaunchToNotificationSetting() {
        return isHasLaunchToNotificationSetting;
    }
}
