package com.aib.fragment

import android.os.Bundle
import com.aib.p2p.R
import com.aib.activity.SettingsActivity
import com.aib.lib.base.image.GlideManager
import com.aib.lib.base.sp.SpKeyConstant
import com.aib.activity.UserInfoActivity
import com.aib.activity.ChongZhiActivity
import com.aib.activity.TiXianActivity
import com.aib.lib.base.arouter.ArouterPath
import com.aib.lib.base.event.EventCode
import com.aib.lib.base.event.EventData
import com.aib.lib.base.fragment.BaseFragment
import com.aib.utils.UserUtils
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.*
import kotlinx.android.synthetic.main.fragment_center.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class CenterFragment : BaseFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(this)
    }

    override fun getLayoutId(): Int = R.layout.fragment_center

    override fun initData() {
        openSettings()
        openAvatar()
        loadPersonalInfo()
        enterUserCenter()
        openRechange()
        openWithdraw()
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
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

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    fun onEvent(data: EventData<Any>) {
        when (data.code) {
            EventCode.CODE_UPDATE_USER -> {
                loadPersonalInfo()
            }
        }
        EventBus.getDefault().removeStickyEvent(data)
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
