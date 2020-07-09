package com.aib.lib.base.net

import com.aib.lib.base.arouter.ArouterPath
import com.aib.lib.base.bean.BaseBean
import com.alibaba.android.arouter.launcher.ARouter

/**
 * 扩展BaseBean函数，进行数据转换
 */
fun <D> BaseBean<D>.convert(): D {
    when (code) {
        0 -> return data
        1 -> {
            //去注册
            ARouter.getInstance().build(ArouterPath.PATH_REGISTER).navigation()
            return data
        }
        else -> throw Exception("客户端出错")
    }
}