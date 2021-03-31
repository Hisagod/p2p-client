package com.aib.fragment

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.aib.activity.ChongZhiActivity
import com.aib.activity.TiXianActivity
import com.aib.base.fragment.BaseLazyFragment
import com.aib.p2p.R
import com.aib.sdk.arouter.ArouterManager
import com.aib.sdk.arouter.ArouterPath
import com.aib.sdk.event.EventCode
import com.aib.sdk.event.EventData
import com.aib.sdk.sp.SpKeyConstant
import com.aib.utils.GlideManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.SPStaticUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_center.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import javax.inject.Inject

@AndroidEntryPoint
@Route(path = ArouterPath.PATH_MINE_PAGE)
class CenterFragment : BaseLazyFragment<ViewDataBinding>() {
    @Inject
    lateinit var arouter: ArouterManager

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
            arouter.openNext(ArouterPath.PATH_USER_INFO)
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
            arouter.openNext(ArouterPath.PATH_USER_INFO)
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
            arouter.openNext(ArouterPath.PATH_SETTINGS)
        }
    }
}
