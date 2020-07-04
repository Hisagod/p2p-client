package com.aib.activity

import com.aib.lib.base.activity.BaseToolbarActivity
import com.aib.lib.base.arouter.ArouterPath
import com.aib.p2p.R
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.ActivityUtils
import kotlinx.android.synthetic.main.activity_login.*

/**
 * 登录
 */
class LoginActivity : BaseToolbarActivity() {
    override fun setTitle(): String {
        return "登录"
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun initData() {
        register()
    }


    /**
     * 注册
     */
    private fun register() {
        tv_register.setOnClickListener {
            ARouter.getInstance().build(ArouterPath.PATH_REGISTER).navigation()
        }
    }
}