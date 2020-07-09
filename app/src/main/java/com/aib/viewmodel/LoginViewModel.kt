package com.aib.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.aib.lib.base.bean.UserBean
import com.aib.lib.base.net.convert
import kotlinx.coroutines.launch

class LoginViewModel : BaseViewModel() {
    fun login(phone: String, pwd: String): LiveData<UserBean> {
        val data = MutableLiveData<UserBean>()
        viewModelScope.launch {
            val bean = api.login(phone, pwd).convert()
            data.value = bean
        }
        return data
    }
}