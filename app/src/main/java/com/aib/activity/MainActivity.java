package com.aib.activity;

import androidx.annotation.NonNull;

import com.aib.fragment.ProductFragment;
import com.aib.lib.base.activity.BaseActivity;
import com.aib.lib.base.arouter.ArouterPath;
import com.aib.p2p.R;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.MenuItem;

import com.aib.fragment.CenterFragment;
import com.aib.fragment.HomeFragment;


import java.util.ArrayList;
import java.util.List;

@Route(path = ArouterPath.PATH_MAIN)
public class MainActivity extends BaseActivity {
    private List<Fragment> fragmentList = new ArrayList<>();

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

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
        fragmentList.add(new HomeFragment());
        fragmentList.add(new ProductFragment());
        fragmentList.add(new CenterFragment());

        BottomNavigationView bnv = findViewById(R.id.bnv);
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
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
                }
                return false;
            }
        });

        switchFragment(0);
    }
}
