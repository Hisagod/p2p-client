package com.aib.viewmodel

import com.aib.net.ApiService
import javax.inject.Inject

open class BaseViewModel @Inject constructor() {
    @Inject
    protected lateinit var apiService: ApiService
}