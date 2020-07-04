package com.aib.fragment;

import android.widget.ListView;
import android.widget.TextView;

import com.aib.lib.base.fragment.BaseFragment;
import com.aib.p2p.R;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.atguigu.p2pinvest0828.adapter.ProductAdapter3;
import com.atguigu.p2pinvest0828.bean.Product;

import java.util.List;


/**
 * Created by shkstart on 2016/12/5 0005.
 * ListView的使用：①ListView ②BaseAdapter ③Item Layout ④集合数据 (联网获取数据）
 */
public class ProductListFragment extends BaseFragment {
    TextView tvProductTitle;
    ListView lvProductList;
    private List<Product> productList;


//    @Override
//    protected String getUrl() {
//        return AppNetConfig.PRODUCT;
//    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_productlist;
    }

    @Override
    public void initData() {
        //方式一：使得当前的textView获取焦点
//        tvProductTitle.setFocusable(true);
//        tvProductTitle.setFocusableInTouchMode(true);
//        tvProductTitle.requestFocus();
        //方式二：提供TextView的子类，重写isFocus(),返回true即可。

        JSONObject jsonObject = JSON.parseObject("");
        boolean success = jsonObject.getBoolean("success");
        if(success){
            String data = jsonObject.getString("data");
            //获取集合数据
            productList = JSON.parseArray(data, Product.class);

            //方式一：没有抽取
//            ProductAdapter productAdapter = new ProductAdapter(productList);
//            lvProductList.setAdapter(productAdapter);//显示列表

//            //方式二：抽取了，但是抽取力度小 （可以作为选择）
//            ProductAdapter1 productAdapter1 = new ProductAdapter1(productList);
//            lvProductList.setAdapter(productAdapter1);//显示列表

            //方式三：抽取了，但是没有使用ViewHolder，getView()优化的不够
//            ProductAdapter2 productAdapter2 = new ProductAdapter2(productList);
//            lvProductList.setAdapter(productAdapter2);//显示列表

            //方式四：抽取了，最好的方式.（可以作为选择）
            ProductAdapter3 productAdapter3 = new ProductAdapter3(productList);
            lvProductList.setAdapter(productAdapter3);//显示列表
        }
    }
}
