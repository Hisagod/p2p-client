package com.aib.net

import com.aib.bean.BannerBean
import com.aib.bean.BaseBean
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("")
    @FormUrlEncoded
    fun REGISTER(@Field("phone") phone: String, @Field("pwd") pwd: String): Observable<BaseBean<String>>

    //获取Banner数据
    @GET("banner/getBannerList")
    suspend fun getBanner(): BaseBean<List<BannerBean>>
}