package com.aib.di

import com.aib.view.fragment.CenterFragment
import com.aib.view.fragment.HomeFragment
import com.aib.view.fragment.InvestFragment
import com.aib.view.fragment.ProductRecommondFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentModule {
    /**
     * 首页
     */
    @ContributesAndroidInjector
    fun HomeFragment(): HomeFragment

    /**
     * 投资
     */
    @ContributesAndroidInjector
    fun InvestFragment(): InvestFragment

    @ContributesAndroidInjector
    fun ProductRecommondFragment(): ProductRecommondFragment

    /**
     * 个人中心
     */
    @ContributesAndroidInjector
    fun CenterFragment(): CenterFragment
}