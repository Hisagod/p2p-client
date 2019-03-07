package com.aib.activity;

import android.os.Bundle;

import com.atguigu.p2pinvest0828.R;
import com.atguigu.p2pinvest0828.databinding.ActivityAboutBinding;

import org.jetbrains.annotations.Nullable;

/**
 * 关于app
 */
public class AboutActivity extends BaseOldActivity<ActivityAboutBinding> {
    @Override
    protected int getResId() {
        return R.layout.activity_about;
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        binding.setContr(this);
    }

    /**
     * 关闭页面
     */
    public void close() {
        finish();
    }
}
