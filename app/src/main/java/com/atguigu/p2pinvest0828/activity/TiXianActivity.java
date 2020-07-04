package com.atguigu.p2pinvest0828.activity;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aib.p2p.R;
import com.atguigu.p2pinvest0828.common.BaseActivity;
import com.atguigu.p2pinvest0828.util.UIUtils;



public class TiXianActivity extends BaseActivity {

    ImageView ivTitleBack;
    TextView tvTitle;
    ImageView ivTitleSetting;
    TextView accountZhifubao;
    RelativeLayout selectBank;
    TextView chongzhiText;
    View view;
    EditText etInputMoney;
    TextView chongzhiText2;
    TextView textView5;
    Button btnTixian;

    @Override
    protected void initData() {
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
                if(TextUtils.isEmpty(money)){
                    //设置button不可操作的
                    btnTixian.setClickable(false);
                    //修改背景颜色
                    btnTixian.setBackgroundResource(R.drawable.btn_02);
                }else{
                    //设置button可操作的
                    btnTixian.setClickable(true);
                    //修改背景颜色
                    btnTixian.setBackgroundResource(R.drawable.btn_01);
                }
            }
        });
    }

    @Override
    protected void initTitle() {
        ivTitleBack.setVisibility(View.VISIBLE);
        tvTitle.setText("提现");
        ivTitleSetting.setVisibility(View.INVISIBLE);
    }

    public void back(View view){
        removeCurrentActivity();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ti_xian;
    }

    public void tiXian(View view){
        //将要提现的数据数额发送给后台，由后台连接第三方支付平台，完成金额的提现操作。（略）
        //提示用户信息：
        UIUtils.toast("您的提现申请已被成功受理。审核通过后，24小时内，你的钱自然会到账",false);

        UIUtils.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                removeCurrentActivity();
            }
        },2000);
    }

}
