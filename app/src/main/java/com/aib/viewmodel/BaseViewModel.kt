package com.aib.viewmodel

import androidx.lifecycle.ViewModel
import com.aib.lib.base.net.ApiService
import com.aib.lib.base.net.RetrofitManager

open class BaseViewModel :ViewModel() {
    protected val api by lazy { RetrofitManager.getInstance.getApiService(ApiService::class.java) }
}