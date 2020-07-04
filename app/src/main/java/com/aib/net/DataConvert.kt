package com.aib.net

import com.aib.bean.BaseBean

/**
 * 扩展BaseBean函数，进行数据转换
 */
fun <D> BaseBean<D>.convert(): D {
    when (code) {
        0 -> return data
        else -> throw Exception("客户端出错")
    }
}