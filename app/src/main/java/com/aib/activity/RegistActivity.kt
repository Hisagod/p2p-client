package com.aib.activity

import android.text.TextUtils
import androidx.lifecycle.Observer
import com.aib.lib.base.activity.BaseToolbarActivity
import com.aib.lib.base.arouter.ArouterPath
import com.aib.lib.base.expand.getViewModel
import com.aib.lib.base.expand.showDialog
import com.aib.lib.base.net.NetStatus
import com.aib.p2p.R
import com.aib.viewmodel.RegisterViewModel
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.EncryptUtils
import com.blankj.utilcode.util.RegexUtils
import com.blankj.utilcode.util.ToastUtils
import kotlinx.android.synthetic.main.activity_user_regist.*

@Route(path = ArouterPath.PATH_REGISTER)
class RegistActivity : BaseToolbarActivity() {
    private val vm by lazy { getViewModel(RegisterViewModel::class.java) }

    override fun setTitle(): String = "注册"

    override fun getLayoutId(): Int {
        return R.layout.activity_user_regist
    }

    override fun initData() {
        register()
    }

    fun register() {
        btn_register.setOnClickListener {
            val getPhone = et_phone.text.toString().trim()
            val getPwd = et_pwd.text.toString().trim()

            if (TextUtils.isEmpty(getPhone)) {
                ToastUtils.showShort("请输入手机号")
                return@setOnClickListener
            }

            if (!RegexUtils.isMobileExact(getPhone)) {
                ToastUtils.showShort("请输入正确手机号")
                return@setOnClickListener
            }

            if (TextUtils.isEmpty(getPwd)) {
                ToastUtils.showShort("请输入密码")
                return@setOnClickListener
            }

            showDialog { dialog ->
                vm.userRegister(getPhone, EncryptUtils.encryptMD5ToString(getPwd)).observe(this, Observer {
                    when (it.status) {
                        NetStatus.LOAD -> TODO()
                        NetStatus.SUCCESS -> {
                            dialog.dismiss()
                            finish()
                        }
                        NetStatus.ERROR -> {
                            dialog.dismiss()
                            ToastUtils.showShort(it.msg)
                        }
                        NetStatus.EMPTY -> TODO()
                    }
                })
            }
        }
    }
}