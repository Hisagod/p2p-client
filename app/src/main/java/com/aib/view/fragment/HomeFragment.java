package com.aib.view.fragment;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aib.view.fragment.BaseFragment;
import com.aib.viewmodel.SplashViewModel;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.atguigu.p2pinvest0828.R;
import com.atguigu.p2pinvest0828.bean.Image;
import com.atguigu.p2pinvest0828.bean.Index;
import com.atguigu.p2pinvest0828.bean.Product;
import com.atguigu.p2pinvest0828.common.AppNetConfig;
import com.atguigu.p2pinvest0828.ui.RoundProgress;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

public class HomeFragment extends BaseFragment<ViewDataBinding> {
    @Override
    public int getResId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
    }
}
