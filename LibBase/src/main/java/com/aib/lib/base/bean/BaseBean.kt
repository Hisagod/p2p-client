package com.aib.lib.base.bean

import java.io.Serializable

data class BaseBean<D>(var code: Int, var msg: String, var data: D) : Serializable