package com.aib

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity

import cn.sharesdk.framework.ShareSDK
import com.aib.di.DaggerAppComponent
import com.aib.di.Injectable
import com.aib.view.activity.BaseActivity
import com.aib.view.fragment.BaseFragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class P2pApplication : Application(), HasActivityInjector {
    @Inject
    lateinit var activityInject: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityInject
    }

    override fun onCreate() {
        super.onCreate()

        context = this.applicationContext
        handler = Handler()
        mainThread = Thread.currentThread()//实例化当前Application的线程即为主线程
        mainThreadId = android.os.Process.myTid()//获取当前线程的id

        //初始化ShareSDK
        ShareSDK.initSDK(this)

        DaggerAppComponent.create().inject(this)
        registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
            override fun onActivityPaused(activity: Activity?) {

            }

            override fun onActivityResumed(activity: Activity?) {
            }

            override fun onActivityStarted(activity: Activity?) {
            }

            override fun onActivityDestroyed(activity: Activity?) {
            }

            override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
            }

            override fun onActivityStopped(activity: Activity?) {
            }

            override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
                if (activity is BaseActivity<*>) {
                    AndroidInjection.inject(activity)

                    activity.supportFragmentManager.registerFragmentLifecycleCallbacks(object : FragmentManager.FragmentLifecycleCallbacks() {
                        override fun onFragmentCreated(fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?) {
                            super.onFragmentCreated(fm, f, savedInstanceState)
                            if (f is BaseFragment<*>) {
                                AndroidSupportInjection.inject(f)
                            }
                        }
                    }, true)
                }
            }
        })
    }

    companion object {

        //在整个应用执行过程中，需要提供的变量
        lateinit var context: Context//需要使用的上下文对象：application实例
        lateinit var handler: Handler//需要使用的handler
        lateinit var mainThread: Thread//提供主线程对象
        var mainThreadId: Int = 0//提供主线程对象的id
    }
}
