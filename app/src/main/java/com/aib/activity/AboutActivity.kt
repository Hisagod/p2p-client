package com.aib.activity

import androidx.activity.viewModels
import com.aib.base.activity.BaseToolbarActivity
import com.aib.p2p.R
import com.aib.p2p.databinding.ActivityAboutBinding
import com.aib.sdk.arouter.ArouterPath
import com.aib.viewmodel.AboutViewModel
import com.alibaba.android.arouter.facade.annotation.Route
import dagger.hilt.android.AndroidEntryPoint

/**
 * 关于app
 */
@AndroidEntryPoint
@Route(path = ArouterPath.PATH_ABOUT_US)
class AboutActivity : BaseToolbarActivity<ActivityAboutBinding>() {

    private val vm by viewModels<AboutViewModel>()

    override fun setTitle(): String {
        return "关于"
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_about
    }

    override fun initData() {

    }
}