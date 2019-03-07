package com.aib.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.atguigu.p2pinvest0828.R;
import com.atguigu.p2pinvest0828.databinding.FragmentInvestBinding;
import com.atguigu.p2pinvest0828.util.UIUtils;
import org.jetbrains.annotations.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * 投资
 */
public class InvestFragment extends BaseOldFragment<FragmentInvestBinding> {
    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    public int getResId() {
        return R.layout.fragment_invest;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        ProductListFragment productListFragment = new ProductListFragment();
        ProductRecommondFragment productRecommondFragment = new ProductRecommondFragment();
        ProductHotFragment productHotFragment = new ProductHotFragment();
        //添加到集合中
        fragmentList.add(productListFragment);
        fragmentList.add(productRecommondFragment);
        fragmentList.add(productHotFragment);
        //2.ViewPager设置三个Fragment的显示
        MyAdapter adapter = new MyAdapter(getFragmentManager());
        binding.vp.setAdapter(adapter);
        //将TabPagerIndicator与ViewPager关联
        binding.tl.setupWithViewPager(binding.vp);
    }

    /**
     * 提供PagerAdapter的实现
     * 如果ViewPager中加载的是Fragment,则提供的Adpater可以继承于具体的：FragmentStatePagerAdapter或FragmentPagerAdapter
     * FragmentStatePagerAdapter:适用于ViewPager中加载的Fragment过多，会根据最近最少使用算法，实现内存中Fragment的清理，避免溢出
     * FragmentPagerAdapter:适用于ViewPager中加载的Fragment不多时，系统不会清理已经加载的Fragment。
     */
    class MyAdapter extends FragmentPagerAdapter {


        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList == null ? 0 : fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return UIUtils.getStringArr(R.array.invest_tab)[position];
        }
    }

}
