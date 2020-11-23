package com.aib.activity;

import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aib.lib.base.activity.BaseToolbarActivity;
import com.aib.p2p.R;
import com.alipay.sdk.app.PayTask;

import org.jetbrains.annotations.NotNull;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;


public class ChongZhiActivity extends BaseToolbarActivity {

    //支付的信息
    private static final int SDK_PAY_FLAG = 1;
    EditText etChongzhi;
    Button btnChongzhi;

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                default:
                    break;
            }
        }

        ;
    };


    @NotNull
    @Override
    public String setTitle() {
        return "充值";
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_chong_zhi;
    }


    @Override
    public void initData() {
        initView();
        //默认情况下，button是不可操作的
        btnChongzhi.setClickable(false);

        etChongzhi.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.e("TAG", "ChongZhiActivity beforeTextChanged()");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.e("TAG", "ChongZhiActivity onTextChanged()");
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.e("TAG", "ChongZhiActivity afterTextChanged()");

                String money = etChongzhi.getText().toString().trim();
                if (TextUtils.isEmpty(money)) {
                    //设置button为不可操作的
                    btnChongzhi.setClickable(false);
                    //设置背景颜色
                    btnChongzhi.setBackgroundResource(R.drawable.btn_02);
                } else {
                    //设置button为可操作的
                    btnChongzhi.setClickable(true);
                    //设置背景颜色
                    btnChongzhi.setBackgroundResource(R.drawable.btn_01);
                }
            }
        });
    }

    private void initView() {
        btnChongzhi = findViewById(R.id.btn_chongzhi);
        etChongzhi = findViewById(R.id.et_chongzhi);
    }

    /**
     * get the sign type we use. 获取签名方式
     */
    public String getSignType() {
        return "sign_type=\"RSA\"";
    }



    /**
     * get the out_trade_no for an order. 生成商户订单号，该值在商户端应保持唯一（可自定义格式规范）
     */
    public String getOutTradeNo() {
        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss",
                Locale.getDefault());
        Date date = new Date();
        String key = format.format(date);

        Random r = new Random();
        key = key + r.nextInt();//next(32)
        key = key.substring(0, 15);
        return key;
    }
}
