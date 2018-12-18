package com.aib.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.aib.view.fragment.AssetsFragment;
import com.aib.view.fragment.InvestFragment;
import com.atguigu.p2pinvest0828.R;
import com.atguigu.p2pinvest0828.databinding.ActivityMainBinding;
import com.aib.view.fragment.HomeFragment;
import com.aib.view.fragment.MoreFragment;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity<ActivityMainBinding> {
    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected int getResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        fragmentList.add(new HomeFragment());
        fragmentList.add(new InvestFragment());
        fragmentList.add(new AssetsFragment());
        fragmentList.add(new MoreFragment());

        binding.bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.btn_home:
                        switchFragment(0);
                        return true;
                    case R.id.btn_invest:
                        switchFragment(1);
                        return true;
                    case R.id.btn_assets:
                        switchFragment(2);
                        return true;
                    case R.id.btn_more:
                        switchFragment(3);
                        return true;
                }
                return false;
            }
        });

        switchFragment(0);
    }

    private void switchFragment(int position) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        for (int i = 0; i < fragmentList.size(); i++) {
            Fragment fragment = fragmentList.get(i);
            if (position == i) {
                if (fragment.isAdded()) {
                    ft.show(fragment);
                } else {
                    ft.add(R.id.fl, fragment);
                }
            } else {
                if (fragment.isAdded()) {
                    ft.hide(fragment);
                }
            }
        }
        ft.commit();
    }
}
