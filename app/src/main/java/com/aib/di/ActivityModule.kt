package com.aib.di

import com.aib.view.activity.MainActivity
import com.aib.view.activity.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [
    FragmentModule::class
])
abstract class ActivityModule {
    /**
     * 欢迎界面
     */
    @ContributesAndroidInjector
    abstract fun SplashActivity(): SplashActivity

    /**
     * 主Activity
     */
    @ContributesAndroidInjector
    abstract fun MainActivity(): MainActivity
}