package com.aib.view.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aib.utils.Constants;
import com.atguigu.p2pinvest0828.R;
import com.atguigu.p2pinvest0828.activity.BarChartActivity;
import com.atguigu.p2pinvest0828.activity.ChongZhiActivity;
import com.atguigu.p2pinvest0828.activity.GestureVerifyActivity;
import com.atguigu.p2pinvest0828.activity.LineChartActivity;
import com.aib.view.activity.LoginActivity;
import com.atguigu.p2pinvest0828.activity.PieChartActivity;
import com.atguigu.p2pinvest0828.activity.TiXianActivity;
import com.atguigu.p2pinvest0828.activity.UserInfoActivity;
import com.atguigu.p2pinvest0828.bean.User;
import com.atguigu.p2pinvest0828.common.BaseActivity;
import com.atguigu.p2pinvest0828.databinding.FragmentAssetsBinding;
import com.atguigu.p2pinvest0828.util.BitmapUtils;
import com.atguigu.p2pinvest0828.util.UIUtils;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import org.jetbrains.annotations.Nullable;

import java.io.File;

/**
 * 资产
 */
public class AssetsFragment extends BaseFragment<FragmentAssetsBinding> {
    ImageView ivTitleBack;
    TextView tvTitle;
    ImageView ivTitleSetting;
    ImageView ivMeIcon;
    TextView tvMeName;

    //加载用户信息并显示
    private void doUser() {
        //1.读取本地保存的用户信息
        User user = ((BaseActivity) this.getActivity()).readUser();
        //2.获取对象信息，并设置给相应的视图显示。
        tvMeName.setText(user.getName());

        //判断本地是否已经保存头像的图片，如果有，则不再执行联网操作
        boolean isExist = readImage();
        if (isExist) {
            return;
        }

        //使用Picasso联网获取图片
        Picasso.with(this.getActivity()).load(user.getImageurl()).transform(new Transformation() {
            @Override
            public Bitmap transform(Bitmap source) {//下载以后的内存中的bitmap对象
                //压缩处理
                Bitmap bitmap = BitmapUtils.zoom(source, UIUtils.dp2px(62), UIUtils.dp2px(62));
                //圆形处理
                bitmap = BitmapUtils.circleBitmap(bitmap);
                //回收bitmap资源
                source.recycle();
                return bitmap;
            }

            @Override
            public String key() {
                return "";//需要保证返回值不能为null。否则报错
            }
        }).into(ivMeIcon);


        //判断一下，是否开启了手势密码。如果开启：先输入手势密码
        SharedPreferences sp = this.getActivity().getSharedPreferences("secret_protect", Context.MODE_PRIVATE);
        boolean isOpen = sp.getBoolean("isOpen", false);
        if (isOpen) {
            ((BaseActivity) this.getActivity()).goToActivity(GestureVerifyActivity.class, null);
            return;
        }
    }

    public void initTitle() {
        ivTitleBack.setVisibility(View.INVISIBLE);
        tvTitle.setText("我的资产");
        ivTitleSetting.setVisibility(View.VISIBLE);
    }

    public void setting(View view) {
        //启动用户信息界面的Activity
        ((BaseActivity) this.getActivity()).goToActivity(UserInfoActivity.class, null);
    }

    @Override
    public void onResume() {
        super.onResume();
        //读取本地保存的图片
        readImage();
    }

    private boolean readImage() {
        File filesDir;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {//判断sd卡是否挂载
            //路径1：storage/sdcard/Android/data/包名/files
            filesDir = this.getActivity().getExternalFilesDir("");

        } else {//手机内部存储
            //路径：data/data/包名/files
            filesDir = this.getActivity().getFilesDir();

        }
        File file = new File(filesDir, "icon.png");
        if (file.exists()) {
            //存储--->内存
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            ivMeIcon.setImageBitmap(bitmap);
            return true;
        }
        return false;

    }

    //设置“充值”操作
    public void reCharge(View view) {
        ((BaseActivity) this.getActivity()).goToActivity(ChongZhiActivity.class, null);
    }

    //设置“提现”操作
    public void withdraw(View view) {
        ((BaseActivity) this.getActivity()).goToActivity(TiXianActivity.class, null);
    }

    //启动折线图
    public void startLineChart(View view) {
        ((BaseActivity) this.getActivity()).goToActivity(LineChartActivity.class, null);
    }

    //启动折线图
    public void startBarChart(View view) {
        ((BaseActivity) this.getActivity()).goToActivity(BarChartActivity.class, null);
    }

    //启动折线图
    public void startPieChart(View view) {
        ((BaseActivity) this.getActivity()).goToActivity(PieChartActivity.class, null);
    }

    @Override
    public int getResId() {
        return R.layout.fragment_assets;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        binding.setContr(this);
    }

    /**
     * 打开图库
     */
    public void openGallery() {
        ThreadUtils.executeByIo(new ThreadUtils.SimpleTask<String>() {
            @android.support.annotation.Nullable
            @Override
            public String doInBackground() throws Throwable {
                return SPUtils.getInstance().getString(Constants.INSTANCE.getTOKEN());
            }

            @Override
            public void onSuccess(@android.support.annotation.Nullable String result) {
                if (TextUtils.isEmpty(result)) {
                    ActivityUtils.startActivity(LoginActivity.class);
                } else {

                }
            }
        });
    }
}
