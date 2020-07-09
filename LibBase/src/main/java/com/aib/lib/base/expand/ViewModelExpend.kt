package com.aib.lib.base.expand

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aib.lib.base.dialog.TipDialog

fun <D : ViewModel> AppCompatActivity.getViewModel(cls: Class<D>): D {
    return ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(cls)
}

fun <D : ViewModel> Fragment.getViewModel(cls: Class<D>): D {
    return ViewModelProvider.AndroidViewModelFactory.getInstance(activity!!.application).create(cls)
}

fun AppCompatActivity.showDialog(callback: (dialog: DialogFragment) -> Unit) {
    val tipDialog = TipDialog()
    tipDialog.isCancelable = false
    tipDialog.show(supportFragmentManager, "")
    callback(tipDialog)
}