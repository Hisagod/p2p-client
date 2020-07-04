package com.aib.viewmodel

import androidx.lifecycle.viewModelScope
import com.aib.lib.base.net.convert
import kotlinx.coroutines.launch

class LoginViewModel : BaseViewModel() {
    fun login(phone: String, pwd: String) {
        viewModelScope.launch {
            val bean = api.login(phone, pwd).convert()
        }
    }
}