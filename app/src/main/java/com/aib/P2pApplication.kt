package com.aib

import android.app.Application
import android.content.Context
import android.os.Handler
import com.aib.p2p.R
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.Utils
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class P2pApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initArouter()
        initUtils()
    }

    private fun initArouter() {
        if (AppUtils.isAppDebug()) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)
    }

    private fun initUtils() {
        Utils.init(this)
    }
}
