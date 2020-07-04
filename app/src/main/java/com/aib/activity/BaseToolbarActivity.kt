package com.aib.activity

import android.view.LayoutInflater
import android.widget.FrameLayout
import com.aib.p2p.R

import kotlinx.android.synthetic.main.view_toolbar_content.*

/**
 * 带Toolbar的基类
 */
abstract class BaseToolbarActivity : BaseActivity(), IBaseToolbarActivity {
    override fun setContentView(layoutResID: Int) {
        val rootView = findViewById<FrameLayout>(android.R.id.content)
        LayoutInflater.from(this).inflate(R.layout.view_toolbar_content, rootView)
        LayoutInflater.from(this).inflate(layoutResID, fl, true)

        closePage()

        setTitle()
    }

    override fun closePage() {
        iv_back.setOnClickListener {
            finish()
        }
    }
}