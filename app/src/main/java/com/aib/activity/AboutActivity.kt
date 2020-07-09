package com.aib.activity

import com.aib.lib.base.activity.BaseToolbarActivity
import com.aib.p2p.R

/**
 * 关于app
 */
class AboutActivity : BaseToolbarActivity() {
    override fun setTitle(): String {
        return "关于"
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_about
    }

    override fun initData() {}
}