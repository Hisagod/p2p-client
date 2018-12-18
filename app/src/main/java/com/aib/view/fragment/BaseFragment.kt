package com.aib.view.fragment

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseFragment<D : ViewDataBinding> : Fragment() {
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