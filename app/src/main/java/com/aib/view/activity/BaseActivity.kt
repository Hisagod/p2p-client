package com.aib.view.activity

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity<D : ViewDataBinding> : AppCompatActivity() {
    lateinit var binding: D

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<D>(this, getResId())

        initData(savedInstanceState)
    }

    protected abstract fun getResId(): Int

    protected abstract fun initData(savedInstanceState: Bundle?)
}