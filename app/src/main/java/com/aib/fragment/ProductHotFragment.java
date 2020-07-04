package com.aib.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aib.lib.base.fragment.BaseFragment;
import com.aib.p2p.R;
import com.atguigu.p2pinvest0828.util.DrawUtils;
import com.atguigu.p2pinvest0828.util.UIUtils;

import org.jetbrains.annotations.Nullable;

import java.util.Random;


/**
 * Created by shkstart on 2016/12/5 0005.
 */
public class ProductHotFragment extends BaseFragment {

    private String[] datas = new String[]{"新手福利计划", "财神道90天计划", "硅谷计划", "30天理财计划", "180天理财计划", "月月升", "中情局投资商业经营", "大学老师购买车辆", "屌丝下海经商计划", "美人鱼影视拍摄投资", "Android培训老师自己周转", "养猪场扩大经营",
            "旅游公司扩大规模", "摩托罗拉洗钱计划", "铁路局回款计划", "屌丝迎娶白富美计划"
    };

    @Override
    public int getLayoutId() {
        return R.layout.fragment_producthot;
    }

    @Override
    public void initData() {
        for (int i = 0; i < datas.length; i++) {
            final TextView tv = new TextView(getContext());
            //设置属性
            tv.setText(datas[i]);
            ViewGroup.MarginLayoutParams mp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            mp.leftMargin = UIUtils.dp2px(5);
            mp.rightMargin = UIUtils.dp2px(5);
            mp.topMargin = UIUtils.dp2px(5);
            mp.bottomMargin = UIUtils.dp2px(5);
            tv.setLayoutParams(mp);//设置边距

            int padding = UIUtils.dp2px(5);
            tv.setPadding(padding, padding, padding, padding);//设置内边距

            tv.setTextSize(UIUtils.dp2px(10));

            Random random = new Random();
            int red = random.nextInt(211);
            int green = random.nextInt(211);
            int blue = random.nextInt(211);
            //设置单一背景
//             tv.setBackground(DrawUtils.getDrawable(Color.rgb(red,green,blue),UIUtils.dp2px(5)));
            //设置具有选择器功能的背景
            tv.setBackground(DrawUtils.getSelector(DrawUtils.getDrawable(Color.rgb(red, green, blue), UIUtils.dp2px(5)), DrawUtils.getDrawable(Color.WHITE, UIUtils.dp2px(5))));
            //设置textView是可点击的.如果设置了点击事件，则TextView就是可点击的。
//             tv.setClickable(true);

            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIUtils.toast(tv.getText().toString(), false);
                }
            });
            ViewGroup view = getView().findViewById(R.id.flow_hot);
            view.addView(tv);
        }
    }
}