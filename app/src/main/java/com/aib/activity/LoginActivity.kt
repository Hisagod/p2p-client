package com.aib.activity

import androidx.activity.viewModels
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.aib.base.activity.BaseToolbarActivity
import com.aib.bean.UserBean
import com.aib.expand.showDialog
import com.aib.net.Status
import com.aib.p2p.R
import com.aib.sdk.arouter.ArouterKey
import com.aib.sdk.arouter.ArouterManager
import com.aib.sdk.arouter.ArouterPath
import com.aib.sdk.event.EventCode
import com.aib.sdk.event.EventData
import com.aib.sdk.sp.SpKeyConstant
import com.aib.viewmodel.LoginViewModel
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_login.*
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject

/**
 * 登录
 */
@Route(path = ArouterPath.PATH_LOGIN)
@AndroidEntryPoint
class LoginActivity : BaseToolbarActivity<ViewDataBinding>() {

    @JvmField
    @Autowired(name = ArouterKey.KEY_PATH)
    var path: String? = null

    @Inject
    lateinit var arouter: ArouterManager

    private val vm by viewModels<LoginViewModel>()

    override fun setTitle(): String = "登录"

    override fun getLayoutId(): Int = R.layout.activity_login

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
                            dialog.dismiss()
                            EventBus.getDefault().postSticky(EventData(EventCode.CODE_UPDATE_USER, null))
                            path?.let { nextPath ->
                                arouter.openNext(nextPath)
                            }
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
}