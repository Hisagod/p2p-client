package com.aib.viewmodel

import android.arch.lifecycle.MutableLiveData
import com.aib.entity.BaseEntity
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RegisterViewModel @Inject constructor() : BaseViewModel() {
    private val data = MutableLiveData<BaseEntity<String>>()
    fun userRegister(phone: String, pwd: String): MutableLiveData<BaseEntity<String>> {
        apiService
                .REGISTER(phone, pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<BaseEntity<String>> {
                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(t: BaseEntity<String>) {
                        data.value = t
                    }

                    override fun onError(e: Throwable) {

                    }
                })
        return data
    }
}