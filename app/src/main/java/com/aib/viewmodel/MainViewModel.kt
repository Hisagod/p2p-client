package com.aib.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aib.bean.HomeBean
import com.aib.bean.ProductBean
import com.aib.net.Resource
import com.aib.net.convert
import com.aib.rep.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRep: MainRepository
) : ViewModel() {

    val mainData = MutableLiveData<Resource<HomeBean>>(Resource.loading(null))

    /**
     * 获取Banner数据
     */
    fun getHome() {
//        mainData.value = Resource.loading(null)
        viewModelScope.launch {
            runCatching {
                val bean = mainRep.loadHomeDataFromNet().convert()
//                mainData.value = Resource.success(bean)
            }.onFailure {
//                mainData.value = Resource.error(it.message ?: "加载失败", null)
            }
        }
    }

    /**
     * 获取产品列表
     */
    fun getProductList(): LiveData<Resource<List<ProductBean>>> {
        val data = MutableLiveData<Resource<List<ProductBean>>>()
        data.value = Resource.loading(null)
        viewModelScope.launch {
            runCatching {
                val bean = mainRep.loadProduct().convert()
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