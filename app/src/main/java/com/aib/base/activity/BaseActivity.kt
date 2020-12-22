package com.aib.base.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.aib.base.IPage
import com.alibaba.android.arouter.launcher.ARouter

abstract class BaseActivity<D : ViewDataBinding> : AppCompatActivity(), IPage {
    lateinit var binding: D
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)

        binding = DataBindingUtil.setContentView(this, getLayoutId())

        initData()
    }
}