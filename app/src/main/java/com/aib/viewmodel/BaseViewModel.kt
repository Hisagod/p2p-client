package com.aib.viewmodel

import androidx.lifecycle.ViewModel
import com.aib.net.ApiService
import com.aib.net.RetrofitManager
import javax.inject.Inject

open class BaseViewModel @Inject constructor():ViewModel() {
    @Inject
    protected lateinit var apiService: ApiService

    protected val api by lazy { RetrofitManager.getInstance.getApiService(ApiService::class.java) }
}