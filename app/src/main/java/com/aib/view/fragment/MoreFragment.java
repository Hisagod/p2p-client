package com.aib.view.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.aib.view.activity.RegistActivity;
import com.atguigu.p2pinvest0828.R;
import com.atguigu.p2pinvest0828.activity.GestureEditActivity;
import com.aib.view.activity.AboutActivity;
import com.atguigu.p2pinvest0828.common.AppNetConfig;
import com.atguigu.p2pinvest0828.common.BaseActivity;
import com.atguigu.p2pinvest0828.databinding.FragmentMoreBinding;
import com.atguigu.p2pinvest0828.util.UIUtils;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.PhoneUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.jetbrains.annotations.Nullable;

import cn.sharesdk.onekeyshare.OnekeyShare;

public class MoreFragment extends BaseFragment<FragmentMoreBinding> {

    private SharedPreferences sp;

    private void share() {
        binding.tvMoreShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShare();
            }
        });
    }

    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
        oks.setTitle(getString(R.string.app_name));
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
        oks.setTitleUrl("http://www.atguigu.com");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("世界上最遥远的距离，是我在if里你在else里，似乎一直相伴又永远分离；\\n\" +\n" +
                "        \"     世界上最痴心的等待，是我当case你是switch，或许永远都选不上自己；\\n\" +\n" +
                "        \"     世界上最真情的相依，是你在try我在catch。无论你发神马脾气，我都默默承受，静静处理。到那时，再来期待我们的finally。");
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://www.atguigu.com");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("word天哪，说的太精辟了！");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("ShareSDK");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

        // 启动分享GUI
        oks.show(this.getActivity());
    }

    private String department = "不明确";

    private void commitFanKui() {
        binding.tvMoreFankui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //提供一个View
                View view = View.inflate(MoreFragment.this.getActivity(), R.layout.view_fankui, null);
                final RadioGroup rg = (RadioGroup) view.findViewById(R.id.rg_fankui);
                final EditText et_fankui_content = (EditText) view.findViewById(R.id.et_fankui_content);

                rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        RadioButton rb = (RadioButton) rg.findViewById(checkedId);
                        department = rb.getText().toString();
                    }
                });

                new AlertDialog.Builder(MoreFragment.this.getActivity())
                        .setView(view)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //获取反馈的信息
                                String content = et_fankui_content.getText().toString();
                                //联网发送反馈信息
                                AsyncHttpClient client = new AsyncHttpClient();
                                String url = AppNetConfig.FEEDBACK;
                                RequestParams params = new RequestParams();
                                params.put("department", department);
                                params.put("content", content);
                                client.post(url, params, new AsyncHttpResponseHandler() {
                                    @Override
                                    public void onSuccess(String content) {
                                        UIUtils.toast("发送反馈信息成功", false);

                                    }

                                    @Override
                                    public void onFailure(Throwable error, String content) {
                                        UIUtils.toast("发送反馈信息失败", false);

                                    }
                                });
                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();

            }
        });
    }

    private void contactService() {
        binding.tvCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MoreFragment.this.getActivity())
                        .setTitle("联系客服")
                        .setMessage("联系客服：1536006XXXX")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @SuppressLint("MissingPermission")
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String phone = binding.tvCustomer.getText().toString().trim();
                                PhoneUtils.call(phone);
                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();
            }
        });
    }


    /**
     * 注册
     */
    public void register() {
        ActivityUtils.startActivity(RegistActivity.class);
    }

    @Override
    public int getResId() {
        return R.layout.fragment_more;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        binding.setContr(this);

        //初始化SharedPreferences
        sp = this.getActivity().getSharedPreferences("secret_protect", Context.MODE_PRIVATE);
        //获取当前设置手势密码的ToggleButton的状态

        //联系客服
        contactService();

        //提交反馈意见
        commitFanKui();

        //分享
        share();
    }

    /**
     * 关于
     */
    public void about() {
        ActivityUtils.startActivity(AboutActivity.class);
    }
}
