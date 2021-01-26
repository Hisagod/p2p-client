package com.aib.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.aib.bean.UserBean
import com.aib.net.Resource
import com.aib.net.convert
import kotlinx.coroutines.launch

class LoginViewModel : BaseViewModel() {
    fun login(phone: String, pwd: String): LiveData<Resource<UserBean>> {
        val data = MutableLiveData<Resource<UserBean>>()
        viewModelScope.launch {
            runCatching {
                val bean = api.login(phone, pwd).convert()
                data.value = Resource.success(bean)
            }.onFailure {
                data.value = Resource.error(it.message ?: "加载失败", null)
            }
        }
        return data
    }
}