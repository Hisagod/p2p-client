package com.aib.activity

import com.aib.lib.base.activity.BaseToolbarActivity
import com.aib.lib.base.arouter.ArouterPath
import com.aib.lib.base.expand.getViewModel
import com.aib.p2p.R
import com.aib.viewmodel.RegisterViewModel
import com.alibaba.android.arouter.facade.annotation.Route

@Route(path = ArouterPath.PATH_REGISTER)
class RegistActivity : BaseToolbarActivity() {
    private val vm by lazy { getViewModel(RegisterViewModel::class.java) }

    override fun setTitle(): String = "注册"

    override fun getLayoutId(): Int {
        return R.layout.activity_user_regist
    }

    override fun initData() {

    }

    fun register() {

//        if (TextUtils.isEmpty(getPhone)) {
//            ToastUtils.showShort("请输入手机号")
//            return
//        }
//
//
//        if (TextUtils.isEmpty(getPwd)) {
//            ToastUtils.showShort("请输入密码")
//            return
//        }
//
//        vm.userRegister(getPhone, EncryptUtils.encryptMD5ToString(getPwd))
//                .observe(this, Observer {
//                    ToastUtils.showShort(it!!.msg)
//                })
    }

}