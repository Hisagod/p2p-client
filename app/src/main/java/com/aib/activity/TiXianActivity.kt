package com.aib.activity

import android.os.Handler
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.aib.base.activity.BaseToolbarActivity
import com.aib.p2p.R
import com.aib.p2p.databinding.ActivityTiXianBinding
import com.aib.sdk.arouter.ArouterPath
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.ToastUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@Route(path = ArouterPath.PATH_WITHDRAW)
class TiXianActivity : BaseToolbarActivity<ActivityTiXianBinding>() {

    override fun setTitle(): String="提现"

    override fun getLayoutId(): Int = R.layout.activity_ti_xian

    override fun initData() {

    }
}