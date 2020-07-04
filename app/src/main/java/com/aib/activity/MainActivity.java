package com.aib.activity;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.aib.p2p.R;
import com.aib.p2p.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.MenuItem;

import com.aib.fragment.CenterFragment;
import com.aib.fragment.InvestFragment;
import com.aib.fragment.HomeFragment;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseOldActivity<ActivityMainBinding> {
    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected int getResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        fragmentList.add(new HomeFragment());
        fragmentList.add(new InvestFragment());
        fragmentList.add(new CenterFragment());

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
