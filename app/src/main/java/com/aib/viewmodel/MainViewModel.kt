package com.aib.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.aib.lib.base.bean.BannerBean
import com.aib.lib.base.net.convert
import kotlinx.coroutines.launch

class MainViewModel : BaseViewModel() {
    /**
     * 获取Banner数据
     */
    fun getBanner(): LiveData<List<BannerBean>> {
        val data = MutableLiveData<List<BannerBean>>()
        viewModelScope.launch {
            val bean = api.getBanner().convert()
            data.value = bean
        }
        return data
    }
}