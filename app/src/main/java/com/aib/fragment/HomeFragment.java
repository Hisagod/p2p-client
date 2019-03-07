package com.aib.fragment;

import androidx.databinding.ViewDataBinding;
import android.os.Bundle;

import com.atguigu.p2pinvest0828.R;

import org.jetbrains.annotations.Nullable;

public class HomeFragment extends BaseOldFragment<ViewDataBinding> {
    @Override
    public int getResId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
    }
}
