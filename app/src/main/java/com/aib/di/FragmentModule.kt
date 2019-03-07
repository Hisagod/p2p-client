package com.aib.di

import com.aib.fragment.CenterFragment
import com.aib.fragment.HomeFragment
import com.aib.fragment.InvestFragment
import com.aib.fragment.ProductRecommondFragment
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