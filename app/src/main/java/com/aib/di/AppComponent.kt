package com.aib.di

import com.aib.P2pApplication
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    ActivityModule::class
])
interface AppComponent {
    fun inject(p2pApplication: P2pApplication)
}