package com.aib.activity

import androidx.lifecycle.Observer
import android.os.Bundle
import android.text.TextUtils
import com.aib.p2p.R
import com.aib.p2p.databinding.ActivityUserRegistBinding
import com.aib.viewmodel.RegisterViewModel

import com.blankj.utilcode.util.EncryptUtils
import com.blankj.utilcode.util.RegexUtils
import com.blankj.utilcode.util.ToastUtils
import javax.inject.Inject


class RegistActivity : BaseOldActivity<ActivityUserRegistBinding>() {
    @Inject
    lateinit var vm: RegisterViewModel

    override fun getResId(): Int {
        return R.layout.activity_user_regist
    }

    override fun initData(savedInstanceState: Bundle?) {
        binding.contr = this
    }

    /**
     * 关闭
     */
    fun close() {
        finish()
    }

    /**
     * 当输入手机号文本改变时
     *
     * @param text
     * @param start
     * @param before
     * @param after
     */
    fun onPhoneTextChange(text: CharSequence, start: Int, before: Int, after: Int) {
        if (RegexUtils.isMobileExact(text)) {
            binding.tilPhone.error = ""
        } else {
            binding.tilPhone.error = "手机号格式不正确"
        }
    }

    fun register() {
        val getPhone = binding.etPhone.text!!.toString().trim { it <= ' ' }
        val getPwd = binding.etPwd.text!!.toString().trim { it <= ' ' }

        if (TextUtils.isEmpty(getPhone)) {
            ToastUtils.showShort("请输入手机号")
            return
        }


        if (TextUtils.isEmpty(getPwd)) {
            ToastUtils.showShort("请输入密码")
            return
        }

        vm
                .userRegister(getPhone, EncryptUtils.encryptMD5ToString(getPwd))
                .observe(this, Observer {
                    ToastUtils.showShort(it!!.msg)
                })
    }
}