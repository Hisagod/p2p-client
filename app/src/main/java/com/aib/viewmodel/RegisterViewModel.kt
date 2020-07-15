package com.aib.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.aib.lib.base.net.Resource
import com.aib.lib.base.net.convert
import kotlinx.coroutines.launch

class RegisterViewModel : BaseViewModel() {
    fun userRegister(phone: String, pwd: String): MutableLiveData<Resource<Any>> {
        val data = MutableLiveData<Resource<Any>>()
        viewModelScope.launch {
            runCatching {
                val bean = api.register(phone, pwd).convert()
                data.value = Resource.success(bean)
            }.onFailure {
                data.value = Resource.error(it.message ?: "获取数据失败")
            }
        }
        return data
    }
}