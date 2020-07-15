package com.aib.lib.base.net

import com.aib.lib.base.arouter.ArouterPath
import com.aib.lib.base.bean.BaseBean
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.ToastUtils

/**
 * 扩展BaseBean函数，进行数据转换
 */
fun <D> BaseBean<D>.convert(): D {
    when (code) {
        -1 -> {
            //服务器出错
            throw Exception("服务器异常")
        }
        0 -> return data
        1 -> {
            //去注册
            finishActivity()
            ARouter.getInstance().build(ArouterPath.PATH_REGISTER).navigation()
            throw Exception(msg)
        }
        2 -> {
            //已注册，去登录
            finishActivity()
            ARouter.getInstance().build(ArouterPath.PATH_LOGIN).navigation()
            throw Exception(msg)
        }
        3 -> {
            //密码错误
            throw Exception(msg)
        }
        else -> throw Exception("其它问题")
    }
}

private fun finishActivity() {
    val topActivity = ActivityUtils.getTopActivity()
    val navigation = ARouter.getInstance().build(ArouterPath.PATH_MAIN).navigation()

    if (topActivity != navigation) {
        topActivity.finish()
    }
}