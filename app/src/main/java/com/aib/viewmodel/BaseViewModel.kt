package com.aib.viewmodel

import androidx.lifecycle.ViewModel
import com.aib.net.ApiService
import com.aib.net.RetrofitManager

open class BaseViewModel :ViewModel() {
    protected val api by lazy { RetrofitManager.getInstance.getApiService(ApiService::class.java) }
}