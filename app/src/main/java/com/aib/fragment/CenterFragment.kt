package com.aib.fragment

import android.os.Bundle
import android.text.TextUtils
import android.view.View

import com.aib.utils.Constants
import com.aib.p2p.R
import com.aib.activity.LoginActivity
import com.aib.activity.SettingsActivity
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.SPUtils
import com.blankj.utilcode.util.ThreadUtils
import kotlinx.android.synthetic.main.fragment_center.*

class CenterFragment : BaseFragment() {
//    fun setting(view: View) {
//        //启动用户信息界面的Activity
//        (this.activity as BaseActivity).goToActivity(UserInfoActivity::class.java, null)
//    }
//
//    //设置“充值”操作
//    fun reCharge(view: View) {
//        (this.activity as BaseActivity).goToActivity(ChongZhiActivity::class.java, null)
//    }
//
//    //设置“提现”操作
//    fun withdraw(view: View) {
//        (this.activity as BaseActivity).goToActivity(TiXianActivity::class.java, null)
//    }
//
//    //启动折线图
//    fun startLineChart(view: View) {
//        (this.activity as BaseActivity).goToActivity(LineChartActivity::class.java, null)
//    }
//
//    //启动折线图
//    fun startBarChart(view: View) {
//        (this.activity as BaseActivity).goToActivity(BarChartActivity::class.java, null)
//    }
//
//    //启动折线图
//    fun startPieChart(view: View) {
//        (this.activity as BaseActivity).goToActivity(PieChartActivity::class.java, null)
//    }

    override fun getResId(): Int = R.layout.fragment_center

    override fun initData(savedInstanceState: Bundle?) {
        openSettings()

        iv_avatar.setOnClickListener {
            ActivityUtils.startActivity(LoginActivity::class.java)
        }
    }

    private fun openSettings() {
        iv_settings.setOnClickListener {
            ActivityUtils.startActivity(SettingsActivity::class.java)
        }
    }

    /**
     * 打开图库
     */
    fun openGallery() {
        ThreadUtils.executeByIo(object : ThreadUtils.SimpleTask<String>() {
            @Throws(Throwable::class)
            override fun doInBackground(): String? {
                return SPUtils.getInstance().getString(Constants.TOKEN)
            }

            override fun onSuccess(result: String?) {
                if (TextUtils.isEmpty(result)) {
                    ActivityUtils.startActivity(LoginActivity::class.java)
                } else {

                }
            }
        })
    }
}
