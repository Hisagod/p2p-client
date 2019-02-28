package com.aib.di

import com.aib.view.fragment.AssetsFragment
import com.aib.view.fragment.HomeFragment
import com.aib.view.fragment.MoreFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentModule {
    @ContributesAndroidInjector
    fun HomeFragment(): HomeFragment

    @ContributesAndroidInjector
    fun MoreFragment(): MoreFragment

    @ContributesAndroidInjector
    fun AssetsFragment(): AssetsFragment
}