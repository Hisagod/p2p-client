package com.aib.view.activity;

import android.os.Bundle;
import android.text.TextUtils;

import com.atguigu.p2pinvest0828.R;
import com.atguigu.p2pinvest0828.databinding.ActivityUserRegistBinding;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.ToastUtils;

import org.jetbrains.annotations.Nullable;


public class UserRegistActivity extends BaseActivity<ActivityUserRegistBinding> {

    @Override
    protected int getResId() {
        return R.layout.activity_user_regist;
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        binding.setContr(this);
    }

    /**
     * 关闭
     */
    public void close() {
        finish();
    }

    /**
     * 当输入手机号文本改变时
     *
     * @param text
     * @param start
     * @param before
     * @param after
     */
    public void onPhoneTextChange(CharSequence text, int start, int before, int after) {
        if (RegexUtils.isMobileExact(text)) {
            binding.tilPhone.setError("");
        } else {
            binding.tilPhone.setError("手机号格式不正确");
        }
    }

    /**
     * 当密码输入框文本改变时
     *
     * @param text
     * @param start
     * @param before
     * @param after
     */
    public void onPwdTextChange(CharSequence text, int start, int before, int after) {

    }

    public void register() {
        String getPhone = binding.etPhone.getText().toString().trim();
        String getPwd = binding.etPwd.getText().toString().trim();

        if (TextUtils.isEmpty(getPhone)) {
            ToastUtils.showShort("请输入手机号");
            return;
        }


        if (TextUtils.isEmpty(getPwd)) {
            ToastUtils.showShort("请输入密码");
            return;
        }
    }
}