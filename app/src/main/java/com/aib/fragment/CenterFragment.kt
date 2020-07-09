package com.aib.fragment

import android.os.Bundle
import android.text.TextUtils

import com.aib.utils.Constants
import com.aib.p2p.R
import com.aib.activity.LoginActivity
import com.aib.activity.SettingsActivity
import com.aib.lib.base.image.GlideManager
import com.aib.lib.base.sp.SpKeyConstant
import com.aib.activity.UserInfoActivity
import com.aib.activity.ChongZhiActivity
import com.aib.activity.TiXianActivity
import com.aib.lib.base.fragment.BaseFragment
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.SPStaticUtils
import com.blankj.utilcode.util.SPUtils
import com.blankj.utilcode.util.ThreadUtils
import kotlinx.android.synthetic.main.fragment_center.*

class CenterFragment : BaseFragment() {

    override fun getLayoutId(): Int =R.layout.fragment_center
    override fun initData() {
        openSettings()

        iv_avatar.setOnClickListener {
            ActivityUtils.startActivity(LoginActivity::class.java)
        }

        loadPersonalInfo()
        enterUserCenter()
        openRechange()
        openWithdraw()
    }

    private fun loadPersonalInfo() {
        GlideManager.getInstance.loadAvatarNetPath(iv_avatar, SPStaticUtils.getString(SpKeyConstant.KEY_STRING_USER_AVATAR))
        tv_me_name.text = SPStaticUtils.getString(SpKeyConstant.KEY_STRING_USER_NAME)
    }

    /**
     * 进入用户中心
     */
    private fun enterUserCenter() {
        rl_me.setOnClickListener {
            ActivityUtils.startActivity(UserInfoActivity::class.java)
        }
    }

    /**
     * 充值
     */
    private fun openRechange() {
        recharge.setOnClickListener {
            ActivityUtils.startActivity(ChongZhiActivity::class.java)
        }
    }

    /**
     * 提现
     */
    private fun openWithdraw() {
        withdraw.setOnClickListener {
            ActivityUtils.startActivity(TiXianActivity::class.java)
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
