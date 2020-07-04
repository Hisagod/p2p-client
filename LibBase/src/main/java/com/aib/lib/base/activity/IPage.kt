package com.aib.lib.base.activity

import com.blankj.utilcode.util.AppUtils

interface IPage {
    //获取布局文件资源
    fun getLayoutId(): Int

    //初始化数据
    fun initData()

    //设置标题
    fun setTitle(): String = AppUtils.getAppName()
}