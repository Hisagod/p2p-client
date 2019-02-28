package com.aib.view.fragment

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

@Deprecated("废弃")
abstract class BaseOldFragment<D : ViewDataBinding> : androidx.fragment.app.Fragment() {
    lateinit var binding: D

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate<D>(inflater, getResId(), container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData(savedInstanceState)
    }

    abstract fun getResId(): Int

    abstract fun initData(savedInstanceState: Bundle?)
}