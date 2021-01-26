package com.aib.activity

import com.aib.base.activity.BaseToolbarActivity
import com.aib.p2p.R
import com.aib.p2p.databinding.ActivityAboutBinding

/**
 * 关于app
 */
class AboutActivity : BaseToolbarActivity<ActivityAboutBinding>() {
    override fun setTitle(): String {
        return "关于"
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_about
    }

    override fun initData() {

    }
}