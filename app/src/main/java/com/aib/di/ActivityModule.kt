package com.aib.di

import com.aib.view.activity.AboutActivity
import com.aib.view.activity.MainActivity
import com.aib.view.activity.SplashActivity
import com.aib.view.activity.RegistActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [
    FragmentModule::class
])
interface ActivityModule {
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

    /**
     * 用户注册
     */
    @ContributesAndroidInjector
    fun UserRegistActivity(): RegistActivity

    /**
     * 关于
     */
    @ContributesAndroidInjector
    fun AboutActivity(): AboutActivity
}