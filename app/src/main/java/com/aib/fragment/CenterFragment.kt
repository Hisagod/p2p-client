package com.aib.fragment

import com.aib.p2p.R
import com.aib.activity.SettingsActivity
import com.aib.lib.base.image.GlideManager
import com.aib.lib.base.sp.SpKeyConstant
import com.aib.activity.UserInfoActivity
import com.aib.activity.ChongZhiActivity
import com.aib.activity.TiXianActivity
import com.aib.lib.base.arouter.ArouterPath
import com.aib.lib.base.fragment.BaseFragment
import com.aib.utils.UserUtils
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.*
import kotlinx.android.synthetic.main.fragment_center.*

class CenterFragment : BaseFragment() {

    override fun getLayoutId(): Int = R.layout.fragment_center
    override fun initData() {
        openSettings()
        openAvatar()
        loadPersonalInfo()
        enterUserCenter()
        openRechange()
        openWithdraw()
    }

    private fun loadPersonalInfo() {
        GlideManager.getInstance.loadAvatarNetPath(iv_avatar, SPStaticUtils.getString(SpKeyConstant.KEY_STRING_USER_AVATAR))
        tv_me_name.text = SPStaticUtils.getString(SpKeyConstant.KEY_STRING_USER_NAME)
    }

    private fun openAvatar() {
        iv_avatar.setOnClickListener {
            if (UserUtils.isLogin()) {
                ActivityUtils.startActivity(UserInfoActivity::class.java)
            } else {
                ARouter.getInstance().build(ArouterPath.PATH_LOGIN).navigation()
            }
        }
    }

    /**
     * 进入用户中心
     */
    private fun enterUserCenter() {
        rl_me.setOnClickListener {
            if (UserUtils.isLogin()) {
                ActivityUtils.startActivity(UserInfoActivity::class.java)
            } else {
                ARouter.getInstance().build(ArouterPath.PATH_LOGIN).navigation()
            }
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
}
