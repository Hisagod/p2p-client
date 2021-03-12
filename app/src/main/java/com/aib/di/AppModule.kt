package com.aib.di

import com.aib.net.ApiService
import com.aib.net.NetConstants
import com.aib.sdk.arouter.ArouterManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Api {
    private val client = {
        OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()
    }

    @Singleton
    @Provides
    fun getApiService(): ApiService {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(NetConstants.BASE_URL)
                .client(client())
                .build()
                .create(ApiService::class.java)
    }

    /**
     * 获取Arouter管理器
     */
    @Singleton
    @Provides
    fun getArouterManager(): ArouterManager = ArouterManager()
}