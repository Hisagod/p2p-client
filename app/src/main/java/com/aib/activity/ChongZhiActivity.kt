package com.aib.activity

import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import com.aib.base.activity.BaseToolbarActivity
import com.aib.p2p.R
import com.aib.p2p.databinding.ActivityChongZhiBinding
import com.aib.sdk.arouter.ArouterPath
import com.alibaba.android.arouter.facade.annotation.Route
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@Route(path = ArouterPath.PATH_RECHARGE)
class ChongZhiActivity : BaseToolbarActivity<ActivityChongZhiBinding>() {
    override fun setTitle(): String {
        return "充值"
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_chong_zhi
    }

    override fun initData() {

    }
}