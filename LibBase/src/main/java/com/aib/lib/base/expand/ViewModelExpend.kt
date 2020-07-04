package com.aib.lib.base.expand

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

fun <D : ViewModel> AppCompatActivity.getViewModel(cls: Class<D>): D {
    return ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(cls)
}

fun <D : ViewModel> Fragment.getViewModel(cls: Class<D>): D {
    return ViewModelProvider.AndroidViewModelFactory.getInstance(activity!!.application).create(cls)
}