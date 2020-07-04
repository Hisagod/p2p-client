package com.atguigu.p2pinvest0828.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aib.p2p.R;
import com.atguigu.p2pinvest0828.bean.Product;
import com.atguigu.p2pinvest0828.ui.RoundProgress;

import java.util.List;


/**
 * Created by shkstart on 2016/12/5 0005.
 */
public class ProductAdapter1 extends MyBaseAdapter1<Product> {


    public ProductAdapter1(List<Product> list) {
        super(list);
    }

    @Override
    public View myGetView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.item_product_list, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //装配数据
        Product product = list.get(position);
        holder.pMinnum.setText(product.memberNum);
        holder.pMinzouzi.setText(product.minTouMoney);
        holder.pMoney.setText(product.money);
        holder.pName.setText(product.name);
        holder.pProgresss.setProgress(Integer.parseInt(product.progress));
        holder.pSuodingdays.setText(product.suodingDays);
        holder.pYearlv.setText(product.yearRate);

        return convertView;
    }

    static class ViewHolder {
        TextView pName;
        TextView pMoney;
        TextView pYearlv;
        TextView pSuodingdays;
        TextView pMinzouzi;
        TextView pMinnum;
        RoundProgress pProgresss;

        ViewHolder(View view) {
        }
    }
}
