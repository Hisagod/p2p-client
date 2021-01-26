package com.aib.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.aib.bean.HomeBean
import com.aib.bean.ProductBean
import com.aib.net.Resource
import com.aib.net.convert
import com.aib.rep.MainRepository
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
        private val mainRep: MainRepository
) : BaseViewModel() {

    val mainData = MutableLiveData<com.aib.net.Resource<HomeBean>>()

    /**
     * 获取Banner数据
     */
    fun getHome() {
        mainData.value = com.aib.net.Resource.loading(null)
        viewModelScope.launch {
            runCatching {
                val bean = mainRep.loadHomeDataFromNet().convert()
                mainData.value = com.aib.net.Resource.success(bean)
            }.onFailure {
                mainData.value = com.aib.net.Resource.error(it.message ?: "加载失败", null)
            }
        }
    }

//    /**
//     * 获取Banner数据
//     */
//    fun getBanner(): LiveData<Resource<HomeBean>> {
//        val data = MutableLiveData<Resource<HomeBean>>()
//        data.value = Resource.load()
//        viewModelScope.launch {
//            runCatching {
//                val bean = api.getHome().convert()
//                if (bean == null) {
//                    data.value = Resource.empty()
//                } else {
//                    data.value = Resource.success(bean)
//                }
//            }.onFailure {
//                data.value = Resource.error(it.message ?: "加载失败")
//            }
//        }
//        return data
//    }

    /**
     * 获取产品列表
     */
    fun getProductList(): LiveData<Resource<List<ProductBean>>> {
        val data = MutableLiveData<Resource<List<ProductBean>>>()
        data.value = Resource.loading(null)
        viewModelScope.launch {
            runCatching {
                val bean = api.getProductList().convert()
                if (bean.isNullOrEmpty()) {
                    data.value = Resource.empty()
                } else {
                    data.value = Resource.success(bean)
                }
            }.onFailure {
                data.value = Resource.error(it.message ?: "加载失败", null)
            }
        }
        return data
    }
}