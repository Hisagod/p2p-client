package com.aib.lib.base.activity

interface IPage {
    //获取布局文件资源
    fun getLayoutId(): Int

    //初始化数据
    fun initData()
}