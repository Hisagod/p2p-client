package com.aib.view.activity

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AlertDialog
import cn.sharesdk.onekeyshare.OnekeyShare
import com.atguigu.p2pinvest0828.R
import com.atguigu.p2pinvest0828.common.AppNetConfig
import com.atguigu.p2pinvest0828.util.UIUtils
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.PhoneUtils
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.loopj.android.http.RequestParams
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        //联系客服
        contactService()

        //提交反馈意见
        commitFanKui()

        //分享
        share()
    }

    private var sp: SharedPreferences? = null

    private fun share() {
        tv_more_share.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                showShare()
            }
        })
    }

    private fun showShare() {
        val oks = OnekeyShare()
        //关闭sso授权
        oks.disableSSOWhenAuthorize()
        // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
        oks.setTitle(getString(R.string.app_name))
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
        oks.setTitleUrl("http://www.atguigu.com")
        // text是分享文本，所有平台都需要这个字段
        oks.setText("世界上最遥远的距离，是我在if里你在else里，似乎一直相伴又永远分离；\\n\" +\n" +
                "        \"     世界上最痴心的等待，是我当case你是switch，或许永远都选不上自己；\\n\" +\n" +
                "        \"     世界上最真情的相依，是你在try我在catch。无论你发神马脾气，我都默默承受，静静处理。到那时，再来期待我们的finally。")
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg")
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://www.atguigu.com")
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("word天哪，说的太精辟了！")
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("ShareSDK")
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn")

        // 启动分享GUI
        oks.show(this)
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

                AlertDialog.Builder(this@SettingsActivity)
                        .setView(view)
                        .setPositiveButton("确定", object : DialogInterface.OnClickListener {
                            override fun onClick(dialog: DialogInterface, which: Int) {
                                //获取反馈的信息
                                val content = et_fankui_content.getText().toString()
                                //联网发送反馈信息
                                val client = AsyncHttpClient()
                                val url = AppNetConfig.FEEDBACK
                                val params = RequestParams()
                                params.put("department", department)
                                params.put("content", content)
                                client.post(url, params, object : AsyncHttpResponseHandler() {
                                    override fun onSuccess(content: String?) {
                                        UIUtils.toast("发送反馈信息成功", false)

                                    }

                                    override fun onFailure(error: Throwable, content: String?) {
                                        UIUtils.toast("发送反馈信息失败", false)

                                    }
                                })
                            }
                        })
                        .setNegativeButton("取消", null)
                        .show()

            }
        })
    }

    private fun contactService() {
        tv_customer.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                AlertDialog.Builder(this@SettingsActivity)
                        .setTitle("联系客服")
                        .setMessage("联系客服：1536006XXXX")
                        .setPositiveButton("确定", object : DialogInterface.OnClickListener {
                            @SuppressLint("MissingPermission")
                            override fun onClick(dialog: DialogInterface, which: Int) {
                                val phone = tv_customer.getText().toString().trim({ it <= ' ' })
                                PhoneUtils.call(phone)
                            }
                        })
                        .setNegativeButton("取消", null)
                        .show()
            }
        })
    }


    /**
     * 注册
     */
    fun register() {
        ActivityUtils.startActivity(RegistActivity::class.java)
    }


    /**
     * 关于
     */
    fun about() {
        ActivityUtils.startActivity(AboutActivity::class.java)
    }
}
