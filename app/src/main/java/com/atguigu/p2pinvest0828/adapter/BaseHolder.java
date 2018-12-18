package com.atguigu.p2pinvest0828.adapter;

import android.view.View;


/**
 * Created by shkstart on 2016/12/5 0005.
 */
public abstract class BaseHolder<T> {
    private View rootView;

    private T data;

    public BaseHolder(){
        rootView = initView();
        rootView.setTag(this);
    }

    //提供item的布局
    protected abstract View initView();

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
        refreshData();
    }
    //装配过程
    protected abstract void refreshData();

    public View getRootView() {
        return rootView;
    }
}
