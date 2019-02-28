package com.aib.view.activity

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

@Deprecated("废弃")
abstract class BaseOldActivity<D : ViewDataBinding> : AppCompatActivity(), HasSupportFragmentInjector {
    lateinit var binding: D
    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<androidx.fragment.app.Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<D>(this, getResId())
        initData(savedInstanceState)
    }

    protected abstract fun getResId(): Int

    protected abstract fun initData(savedInstanceState: Bundle?)

    override fun supportFragmentInjector(): AndroidInjector<androidx.fragment.app.Fragment> {
        return fragmentInjector
    }
}