package com.aib.lib.base.net

import com.aib.lib.base.bean.BannerBean
import com.aib.lib.base.bean.BaseBean
import com.aib.lib.base.bean.UserBean
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

    //用户登录
    @POST("user/login")
    @FormUrlEncoded
    suspend fun login(@Field("phone") phone: String, @Field("pwd") pwd: String): BaseBean<UserBean>
}