package com.aib

import android.app.Application
import android.content.Context
import android.os.Handler
import com.aib.lib.base.BuildConfig
import com.aib.other.DefaultPage
import com.aib.p2p.R
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.Utils
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.FormatStrategy
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy


class P2pApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        context = this.applicationContext
        handler = Handler()
        mainThread = Thread.currentThread()//实例化当前Application的线程即为主线程
        mainThreadId = android.os.Process.myTid()//获取当前线程的id

        initArouter()
        initUtils()
        initLogger()
        initDefaultPage()
    }

    private fun initArouter() {
        if (BuildConfig.DEBUG) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
    }

    private fun initUtils() {
        Utils.init(this)
    }

    private fun initLogger() {
        val formatStrategy: FormatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false) // (Optional) Whether to show thread info or not. Default true
                .methodCount(0) // (Optional) How many method line to show. Default 2
                .methodOffset(7) // (Optional) Hides internal method calls up to offset. Default 5
                .tag("Json") // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build()
        Logger.addLogAdapter(object : AndroidLogAdapter(formatStrategy) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return com.aib.p2p.BuildConfig.DEBUG
            }
        })
    }

    private fun initDefaultPage() {
        DefaultPage.Builder()
                .empty(R.layout.page_empty)
                .load(R.layout.page_loading)
                .error( R.layout.page_error)
                .build()
    }

    companion object {

        //在整个应用执行过程中，需要提供的变量
        lateinit var context: Context//需要使用的上下文对象：application实例
        lateinit var handler: Handler//需要使用的handler
        lateinit var mainThread: Thread//提供主线程对象
        var mainThreadId: Int = 0//提供主线程对象的id
    }
}
