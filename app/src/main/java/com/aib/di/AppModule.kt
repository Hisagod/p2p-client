package com.aib.di

import com.aib.net.ApiService
import com.aib.lib.base.net.NetConstants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * 提供第三方对象
 */
@Module
class AppModule {
    /**
     * 提供ApiService
     */
    @Singleton
    @Provides
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(NetConstants.BASE_URL)
                .build()
                .create(ApiService::class.java)
    }
}