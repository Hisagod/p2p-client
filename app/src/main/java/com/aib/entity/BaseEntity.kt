package com.aib.entity

import java.io.Serializable

data class BaseEntity<D>(var code: Int, var msg: String, var data: D) : Serializable