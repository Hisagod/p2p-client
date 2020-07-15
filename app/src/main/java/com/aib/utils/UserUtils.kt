package com.aib.utils

import com.aib.lib.base.sp.SpKeyConstant
import com.blankj.utilcode.util.SPStaticUtils

class UserUtils {
    companion object {
        /**
         * 用户是否登录
         */
        fun isLogin(): Boolean {
            val uid = SPStaticUtils.getInt(SpKeyConstant.KEY_INT_USER_ID, 0)
            return uid != 0
        }
    }
}