package com.aib.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.widget.TextView;

import com.aib.viewmodel.SplashViewModel;
import com.alibaba.fastjson.JSON;
import com.atguigu.p2pinvest0828.R;
import com.atguigu.p2pinvest0828.bean.UpdateInfo;
import com.atguigu.p2pinvest0828.common.AppNetConfig;
import com.atguigu.p2pinvest0828.databinding.ActivitySplashBinding;
import com.atguigu.p2pinvest0828.util.UIUtils;
import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.inject.Inject;


public class SplashActivity extends BaseOldActivity<ActivitySplashBinding> {
    @Inject
    SplashViewModel vm;
    private static final int TO_MAIN = 1;
    private static final int DOWNLOAD_VERSION_SUCCESS = 2;
    private static final int DOWNLOAD_APK_FAIL = 3;
    private static final int DOWNLOAD_APK_SUCCESS = 4;
    TextView tvWelcomeVersion;
    private long startTime;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case TO_MAIN:
                    finish();
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    break;
                case DOWNLOAD_VERSION_SUCCESS:
                    //获取当前应用的版本信息
                    String version = getVersion();
                    //更新页面显示的版本信息
                    tvWelcomeVersion.setText(version);
                    //比较服务器获取的最新的版本跟本应用的版本是否一致
                    if (version.equals(updateInfo.version)) {
                        UIUtils.toast("当前应用已经是最新版本", false);
                        toMain();
                    } else {
                        new AlertDialog.Builder(SplashActivity.this)
                                .setTitle("下载最新版本")
                                .setMessage(updateInfo.desc)
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //下载服务器保存的应用数据
                                        downloadApk();
                                    }
                                })
                                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        toMain();
                                    }
                                })
                                .show();
                    }

                    break;
                case DOWNLOAD_APK_FAIL:
                    UIUtils.toast("联网下载数据失败", false);
                    toMain();
                    break;
                case DOWNLOAD_APK_SUCCESS:
                    UIUtils.toast("下载应用数据成功", false);
                    dialog.dismiss();
                    installApk();//安装下载好的应用
                    finish();//结束当前的welcomeActivity的显示
                    break;
            }

        }
    };

    private void installApk() {
        Intent intent = new Intent("android.intent.action.INSTALL_PACKAGE");
        intent.setData(Uri.parse("file:" + apkFile.getAbsolutePath()));
        startActivity(intent);
    }

    private ProgressDialog dialog;
    private File apkFile;

    private void downloadApk() {
        //初始化水平进度条的dialog
        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setCancelable(false);
        dialog.show();
        //初始化数据要保持的位置
        File filesDir;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            filesDir = this.getExternalFilesDir("");
        } else {
            filesDir = this.getFilesDir();
        }
        apkFile = new File(filesDir, "update.apk");

        //启动一个分线程联网下载数据：
        new Thread() {
            public void run() {
                String path = updateInfo.apkUrl;
                InputStream is = null;
                FileOutputStream fos = null;
                HttpURLConnection conn = null;
                try {
                    URL url = new URL(path);
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(5000);
                    conn.setReadTimeout(5000);
                    conn.connect();
                    if (conn.getResponseCode() == 200) {
                        dialog.setMax(conn.getContentLength());//设置dialog的最大值
                        is = conn.getInputStream();
                        fos = new FileOutputStream(apkFile);

                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len = is.read(buffer)) != -1) {
                            //更新dialog的进度
                            dialog.incrementProgressBy(len);
                            fos.write(buffer, 0, len);
                            SystemClock.sleep(1);
                        }
                        handler.sendEmptyMessage(DOWNLOAD_APK_SUCCESS);
                    } else {
                        handler.sendEmptyMessage(DOWNLOAD_APK_FAIL);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (conn != null) {
                        conn.disconnect();
                    }
                    if (is != null) {
                        try {
                            is.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fos != null) {
                        try {
                            fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }.start();
    }

    private UpdateInfo updateInfo;

    /**
     * 当前版本号
     *
     * @return
     */
    private String getVersion() {
        String version = "未知版本";
        PackageManager manager = getPackageManager();
        try {
            PackageInfo packageInfo = manager.getPackageInfo(getPackageName(), 0);
            version = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            //e.printStackTrace(); //如果找不到对应的应用包信息, 就返回"未知版本"
        }
        return version;
    }

    private void updateApkFile() {
        //获取系统当前时间
        startTime = System.currentTimeMillis();

        //1.判断手机是否可以联网
        boolean connect = isConnect();
        if (!connect) {//没有移动网络
            UIUtils.toast("当前没有移动数据网络", false);
            toMain();
        } else {//有移动网络
            //联网获取服务器的最新版本数据
            AsyncHttpClient client = new AsyncHttpClient();
            String url = AppNetConfig.UPDATE;
            client.post(url, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(String content) {
                    //解析json数据
                    updateInfo = JSON.parseObject(content, UpdateInfo.class);
                    handler.sendEmptyMessage(DOWNLOAD_VERSION_SUCCESS);
                }

                @Override
                public void onFailure(Throwable error, String content) {
                    UIUtils.toast("联网请求数据失败", false);
                    toMain();
                }
            });

        }
    }

    private void toMain() {
        long currentTime = System.currentTimeMillis();
        long delayTime = 3000 - (currentTime - startTime);
        if (delayTime < 0) {
            delayTime = 0;
        }
        handler.sendEmptyMessageDelayed(TO_MAIN, delayTime);
    }

    public boolean isConnect() {
        boolean connected = false;

        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo != null) {
            connected = networkInfo.isConnected();
        }
        return connected;
    }

    @Override
    protected int getResId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        BarUtils.setStatusBarColor(this, 0);

        PermissionUtils.permission(PermissionConstants.PHONE).callback(new PermissionUtils.FullCallback() {
            @Override
            public void onGranted(List<String> permissionsGranted) {
                new CountDownTimer(3000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                        ActivityUtils.startActivity(MainActivity.class);
                    }
                }.start();
            }

            @Override
            public void onDenied(List<String> permissionsDeniedForever, List<String> permissionsDenied) {
                finish();
            }
        }).request();


        //联网更新应用
//by hlp        updateApkFile();

    }
}
