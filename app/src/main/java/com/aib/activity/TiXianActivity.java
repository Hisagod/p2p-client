package com.aib.activity;

import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.aib.lib.base.activity.BaseToolbarActivity;
import com.aib.p2p.R;
import com.blankj.utilcode.util.ToastUtils;

import org.jetbrains.annotations.NotNull;

public class TiXianActivity extends BaseToolbarActivity {
    EditText etInputMoney;
    Button btnTixian;

    @NotNull
    @Override
    public String setTitle() {
        return "提现";
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_ti_xian;
    }

    @Override
    public void initData() {
        initView();
        //设置当前的体现的button是不可操作的
        btnTixian.setClickable(false);
        etInputMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String money = etInputMoney.getText().toString().trim();
                if (TextUtils.isEmpty(money)) {
                    //设置button不可操作的
                    btnTixian.setClickable(false);
                    //修改背景颜色
                    btnTixian.setBackgroundResource(R.drawable.btn_02);
                } else {
                    //设置button可操作的
                    btnTixian.setClickable(true);
                    //修改背景颜色
                    btnTixian.setBackgroundResource(R.drawable.btn_01);
                }
            }
        });
    }

    private void initView() {
        btnTixian = findViewById(R.id.btn_tixian);
        etInputMoney = findViewById(R.id.et_input_money);
    }

    public void tiXian(View view) {
        //将要提现的数据数额发送给后台，由后台连接第三方支付平台，完成金额的提现操作。（略）
        //提示用户信息：
        ToastUtils.showShort("您的提现申请已被成功受理。审核通过后，24小时内，你的钱自然会到账");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 2000);
    }
}
