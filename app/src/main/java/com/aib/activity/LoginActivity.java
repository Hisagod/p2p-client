package com.aib.activity;

import android.os.Bundle;

import com.atguigu.p2pinvest0828.R;
import com.atguigu.p2pinvest0828.databinding.ActivityLoginBinding;
import com.blankj.utilcode.util.ActivityUtils;
import org.jetbrains.annotations.Nullable;

/**
 * 登录
 */
public class LoginActivity extends BaseOldActivity<ActivityLoginBinding> {
    @Override
    protected int getResId() {
        return R.layout.activity_login;
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

    /**
     * 注册
     */
    public void register() {
        ActivityUtils.startActivity(RegistActivity.class);
    }
}
