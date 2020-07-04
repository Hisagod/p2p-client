package com.atguigu.p2pinvest0828.adapter;

import android.view.View;
import android.widget.TextView;


import com.aib.p2p.R;
import com.atguigu.p2pinvest0828.bean.Product;
import com.atguigu.p2pinvest0828.ui.RoundProgress;
import com.atguigu.p2pinvest0828.util.UIUtils;


/**
 * Created by shkstart on 2016/12/5 0005.
 */
public class MyHolder extends BaseHolder<Product> {

    TextView pName;
    TextView pMoney;
    TextView pYearlv;
    TextView pSuodingdays;
    TextView pMinzouzi;
    TextView pMinnum;
    RoundProgress pProgresss;

    @Override
    protected View initView() {
        return View.inflate(UIUtils.getContext(), R.layout.item_product_list, null);
    }

    @Override
    protected void refreshData() {
        Product data = this.getData();

        //装数据
        pMinnum.setText(data.memberNum);
        pMinzouzi.setText(data.minTouMoney);
        pMoney.setText(data.money);
        pName.setText(data.name);
        pProgresss.setProgress(Integer.parseInt(data.progress));
        pSuodingdays.setText(data.suodingDays);
        pYearlv.setText(data.yearRate);

    }
}
