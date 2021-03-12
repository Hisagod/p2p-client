package com.aib.activity

import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import com.aib.base.activity.BaseToolbarActivity
import com.aib.p2p.R
import com.aib.p2p.databinding.ActivityChongZhiBinding
import com.aib.sdk.arouter.ArouterPath
import com.alibaba.android.arouter.facade.annotation.Route
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@Route(path = ArouterPath.PATH_RECHARGE)
class ChongZhiActivity : BaseToolbarActivity<ActivityChongZhiBinding>() {
    override fun setTitle(): String {
        return "充值"
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_chong_zhi
    }

    override fun initData() {
        //默认情况下，button是不可操作的
        binding.btnChongzhi.isClickable = false
        binding.etChongzhi.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                val money = binding.etChongzhi.text.toString().trim { it <= ' ' }
                if (TextUtils.isEmpty(money)) {
                    //设置button为不可操作的
                    binding.btnChongzhi.isClickable = false
                    //设置背景颜色
                    binding.btnChongzhi.setBackgroundResource(R.drawable.btn_02)
                } else {
                    //设置button为可操作的
                    binding.btnChongzhi.isClickable = true
                    //设置背景颜色
                    binding.btnChongzhi.setBackgroundResource(R.drawable.btn_01)
                }
            }
        })
    }
}