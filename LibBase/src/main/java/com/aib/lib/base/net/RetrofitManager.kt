package com.aib.lib.base.net

import com.blankj.utilcode.util.JsonUtils
import com.blankj.utilcode.util.LogUtils
import com.orhanobut.logger.Logger
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.StringBuilder

class RetrofitManager private constructor() {
    companion object {
        val getInstance: RetrofitManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            RetrofitManager()
        }
    }

    private val client = {
        val data = StringBuilder()
        OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
                    override fun log(message: String) {
                        // 以{}或者[]形式的说明是响应结果的json数据，需要进行格式化
                        if ((message.startsWith("{") && message.endsWith("}")) || (message.startsWith("[") && message.endsWith("]"))) {
                            val formatJson = JsonUtils.formatJson(message)
                            data.append(formatJson)
                        } else {
                            data.append(message).append("\n")
                        }
                        // 响应结束，打印整条日志
                        if (message.startsWith("<-- END HTTP")) {
                            Logger.w(data.toString())
                        }
                    }
                }).setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()
    }

    fun <T> getApiService(clazz: Class<T>): T {
        return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(NetConstants.BASE_URL)
                .client(client())
                .build()
                .create(clazz)
    }
}
