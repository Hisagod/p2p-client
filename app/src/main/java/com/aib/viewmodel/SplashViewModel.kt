package com.aib.viewmodel

import com.blankj.utilcode.util.ToastUtils
import javax.inject.Inject

class SplashViewModel @Inject constructor() : BaseViewModel() {
    fun register() {
        ToastUtils.showShort(apiService.toString())
    }
}