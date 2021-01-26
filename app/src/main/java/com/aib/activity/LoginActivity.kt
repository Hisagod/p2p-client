package com.aib.activity

import androidx.activity.viewModels
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.aib.base.activity.BaseToolbarActivity
import com.aib.sdk.arouter.ArouterPath
import com.aib.bean.UserBean
import com.aib.sdk.event.EventCode
import com.aib.sdk.event.EventData
import com.aib.expand.showDialog
import com.aib.net.Status
import com.aib.sdk.sp.SpKeyConstant
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
class LoginActivity : BaseToolbarActivity<ViewDataBinding>() {
    private val vm by viewModels<LoginViewModel>()

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
                        Status.LOAD -> TODO()
                        Status.SUCCESS -> {
                            saveData(it.data!!)
                            dialog.dismiss()
                            EventBus.getDefault().postSticky(EventData(EventCode.CODE_UPDATE_USER, null))
                            finish()
                        }
                        Status.ERROR -> {
                            dialog.dismiss()
                            ToastUtils.showShort(it.message)
                        }
                        Status.EMPTY -> TODO()
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