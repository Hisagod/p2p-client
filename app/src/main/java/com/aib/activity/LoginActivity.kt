package com.aib.activity

import androidx.lifecycle.Observer
import com.aib.lib.base.activity.BaseToolbarActivity
import com.aib.lib.base.arouter.ArouterPath
import com.aib.lib.base.bean.UserBean
import com.aib.lib.base.event.EventCode
import com.aib.lib.base.event.EventData
import com.aib.lib.base.expand.getViewModel
import com.aib.lib.base.expand.showDialog
import com.aib.lib.base.net.NetStatus
import com.aib.lib.base.sp.SpKeyConstant
import com.aib.p2p.R
import com.aib.viewmodel.LoginViewModel
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.*
import kotlinx.android.synthetic.main.activity_login.*
import org.greenrobot.eventbus.EventBus

/**
 * 登录
 */
@Route(path = ArouterPath.PATH_LOGIN)
class LoginActivity : BaseToolbarActivity() {
    private val vm by lazy { getViewModel(LoginViewModel::class.java) }

    override fun setTitle(): String {
        return "登录"
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun initData() {
        login()
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

    private fun login() {
        btn_login.setOnClickListener {
            val phone = et_phone.text.toString().trim()
            if (phone.isEmpty()) {
                ToastUtils.showShort("请输入账号")
                return@setOnClickListener
            }

            if (!RegexUtils.isMobileExact(phone)) {
                ToastUtils.showShort("请输入正确手机号")
                return@setOnClickListener
            }

            val pwd = et_pwd.text.toString().trim()
            if (phone.isEmpty()) {
                ToastUtils.showShort("请输入密码")
                return@setOnClickListener
            }

            showDialog { dialog ->
                vm.login(phone, EncryptUtils.encryptMD5ToString(pwd)).observe(this, Observer {
                    when (it.status) {
                        NetStatus.LOAD -> TODO()
                        NetStatus.SUCCESS -> {
                            saveData(it.data!!)
                            dialog.dismiss()
                            EventBus.getDefault().postSticky(EventData(EventCode.CODE_UPDATE_USER, null))
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

    private fun saveData(it: UserBean) {
        SPStaticUtils.put(SpKeyConstant.KEY_INT_USER_ID, it.id)
        SPStaticUtils.put(SpKeyConstant.KEY_STRING_USER_AVATAR, it.avatar)
        SPStaticUtils.put(SpKeyConstant.KEY_STRING_USER_NAME, it.name)
    }
}