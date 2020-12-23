package com.aib.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.aib.rep.SplashRepository

class SplashViewModel @ViewModelInject constructor(private val rep: SplashRepository) : ViewModel() {
    fun test() {
        rep.loadDataFromNet()
    }
}