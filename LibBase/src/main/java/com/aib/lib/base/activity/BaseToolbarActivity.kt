package com.aib.lib.base.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.aib.lib.base.R
import com.aib.view.DefaultView
import com.alibaba.android.arouter.launcher.ARouter
import kotlinx.android.synthetic.main.view_toolbar_with_content.*

abstract class BaseToolbarActivity : AppCompatActivity(), IPage {
    internal lateinit var dv: DefaultView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)
        LayoutInflater.from(this).inflate(R.layout.view_toolbar_with_content, findViewById<FrameLayout>(android.R.id.content), true)
        //得到缺省页控件
        dv = dv_content
        //设置标题
        tv_title.text = setTitle()
        LayoutInflater.from(this).inflate(getLayoutId(), dv, true)
        initData()
        close()
    }

    private val close = {
        iv_back.setOnClickListener {
            finish()
        }
    }
}