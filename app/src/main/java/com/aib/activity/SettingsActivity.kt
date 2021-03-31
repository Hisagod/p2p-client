package com.aib.activity

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AlertDialog
import cn.sharesdk.onekeyshare.OnekeyShare
import com.aib.base.activity.BaseToolbarActivity
import com.aib.p2p.R
import com.aib.p2p.databinding.ActivitySettingsBinding
import com.aib.sdk.arouter.ArouterManager
import com.aib.sdk.arouter.ArouterPath
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.PhoneUtils
import com.lxj.xpopup.XPopup
import com.mob.MobSDK
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_settings.*
import javax.inject.Inject

@AndroidEntryPoint
@Route(path = ArouterPath.PATH_SETTINGS)
class SettingsActivity : BaseToolbarActivity<ActivitySettingsBinding>() {
    @Inject
    lateinit var arouter: ArouterManager

    override fun setTitle(): String = "设置中心"

    override fun getLayoutId(): Int = R.layout.activity_settings

    override fun initData() {
        binding.view = this

        //提交反馈意见
        commitFanKui()

        //分享
        share()
    }

    private fun share() {
        tv_more_share.setOnClickListener {
            showShare()
        }
    }

    private fun showShare() {
        val oks = OnekeyShare()
        // title标题，微信、QQ和QQ空间等平台使用
        oks.setTitle("分享")
        // titleUrl QQ和QQ空间跳转链接
        oks.setTitleUrl("http://sharesdk.cn")
        // text是分享文本，所有平台都需要这个字段
        oks.text = "我是分享文本"
        // setImageUrl是网络图片的url
        oks.setImageUrl("https://hmls.hfbank.com.cn/hfapp-api/9.png")
        // url在微信、Facebook等平台中使用
        oks.setUrl("http://sharesdk.cn")
        // 启动分享GUI
        oks.show(MobSDK.getContext())
    }

    private var department = "不明确"

    private fun commitFanKui() {
        tv_more_fankui.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                //提供一个View
                val view = View.inflate(this@SettingsActivity, R.layout.view_fankui, null)
                val rg = view.findViewById(R.id.rg_fankui) as RadioGroup
                val et_fankui_content = view.findViewById(R.id.et_fankui_content) as EditText

                rg.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
                    override fun onCheckedChanged(group: RadioGroup, checkedId: Int) {
                        val rb = rg.findViewById(checkedId) as RadioButton
                        department = rb.getText().toString()
                    }
                })
            }
        })
    }

    //联系客服
    fun contactService() {
        XPopup.Builder(this)
                .asConfirm("联系客服", "联系客服：1536006XXXX", {
//                    PhoneUtils.call(phone)
                }, {

                }).show()
    }

    /**
     * 关于
     */
    fun about() {
        arouter.openNext(ArouterPath.PATH_ABOUT_US)
    }
}
