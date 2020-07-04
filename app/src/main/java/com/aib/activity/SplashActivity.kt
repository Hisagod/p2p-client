package com.aib.activity

import android.os.CountDownTimer
import com.aib.lib.base.activity.BaseActivity
import com.aib.p2p.R
import com.blankj.utilcode.constant.PermissionConstants
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.PermissionUtils
import com.blankj.utilcode.util.PermissionUtils.FullCallback
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun initData() {
        BarUtils.setStatusBarColor(this, 0)

        showVersion()

        PermissionUtils.permission(PermissionConstants.PHONE).callback(object : FullCallback {
            override fun onGranted(permissionsGranted: List<String>) {
                object : CountDownTimer(3000, 1000) {
                    override fun onTick(millisUntilFinished: Long) {}
                    override fun onFinish() {
                        ActivityUtils.startActivity(MainActivity::class.java)
                    }
                }.start()
            }

            override fun onDenied(permissionsDeniedForever: List<String>, permissionsDenied: List<String>) {
                finish()
            }
        }).request()
    }

    private val showVersion = {
        tv_version.text = AppUtils.getAppVersionName()
    }
}