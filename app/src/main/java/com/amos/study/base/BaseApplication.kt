package com.amos.study.base

import android.app.Application
import me.jessyan.autosize.AutoSizeConfig
import me.jessyan.autosize.BuildConfig
import me.jessyan.autosize.unit.Subunits

/**
 * @author: amos
 * @date: 2021/2/20 17:27
 * @description:
 */
class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AutoSizeConfig.getInstance()
            .setLog(BuildConfig.DEBUG)
            .setCustomFragment(true)
            .getUnitsManager()
            .setSupportDP(false)
            .setSupportSP(false)
            .setSupportSubunits(Subunits.PT);
    }
}