package com.aib.activity

import android.os.CountDownTimer
import androidx.activity.viewModels
import com.aib.base.activity.BaseActivity
import com.aib.p2p.R
import com.aib.p2p.databinding.ActivitySplashBinding
import com.aib.sdk.arouter.ArouterManager
import com.aib.sdk.arouter.ArouterPath
import com.aib.viewmodel.SplashViewModel
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.constant.PermissionConstants
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.PermissionUtils
import com.blankj.utilcode.util.PermissionUtils.FullCallback
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
@Route(path = ArouterPath.PATH_SPLASH)
class SplashActivity : BaseActivity<ActivitySplashBinding>() {
    @Inject
    lateinit var manager: ArouterManager
    private val vm by viewModels<SplashViewModel>()

    private val permissions = arrayOf(
            PermissionConstants.PHONE,
            PermissionConstants.STORAGE
    )

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun initData() {
        BarUtils.transparentStatusBar(this)

        binding.tvVersion.text = AppUtils.getAppVersionName()

        PermissionUtils.permission(*permissions).callback(object : FullCallback {
            override fun onGranted(permissionsGranted: List<String>) {
                object : CountDownTimer(3000, 1000) {
                    override fun onTick(millisUntilFinished: Long) {}
                    override fun onFinish() {
                        manager.openMainPage()
                        finish()
                    }
                }.start()
            }

            override fun onDenied(permissionsDeniedForever: List<String>, permissionsDenied: List<String>) {

            }
        }).request()
    }
}