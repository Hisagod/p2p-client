package com.aib.viewmodel

import androidx.lifecycle.MutableLiveData
import com.aib.bean.BaseBean
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RegisterViewModel @Inject constructor() : BaseViewModel() {
    private val data = MutableLiveData<BaseBean<String>>()
    fun userRegister(phone: String, pwd: String): MutableLiveData<BaseBean<String>> {
        apiService
                .REGISTER(phone, pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<BaseBean<String>> {
                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(t: BaseBean<String>) {
                        data.value = t
                    }

                    override fun onError(e: Throwable) {

                    }
                })
        return data
    }
}