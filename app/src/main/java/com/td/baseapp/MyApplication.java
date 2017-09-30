package com.td.baseapp;


import android.app.Application;
import android.content.Context;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.td.baseapp.db.GreenDaoManager;
import com.td.baselibs.utils.PrefUtils;
import com.td.baselibs.utils.SharePreferenceUtil;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import io.rong.imkit.RongIM;
import io.rong.imkit.widget.provider.RealTimeLocationMessageProvider;
import io.rong.imlib.ipc.RongExceptionHandler;
import io.rong.push.RongPushClient;
import io.rong.push.common.RongException;
import okhttp3.OkHttpClient;

import static io.rong.imkit.utils.SystemUtils.getCurProcessName;


/**
 * Created by smile_x on 2016/10/25.
 */

public class MyApplication extends Application {
    private static MyApplication instance;
    public SharePreferenceUtil mPreferenceUtil;
    private Context context;

    public static MyApplication getInstance() { // 通过一个方法给外面提供实例
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        instance = this;
        // sp 工具类
        mPreferenceUtil = new SharePreferenceUtil(this, "");
        PrefUtils.init(this);
        // initOkGo();
        HttpManagerRx.init(this);
        HttpManager.init(this);

        GreenDaoManager.getInstance().init(this);
        if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext()))) {


            /**
             * 注意：
             *
             * IMKit SDK调用第一步 初始化
             *
             * context上下文
             *
             * 只有两个进程需要初始化，主进程和 push 进程
             */
            RongIM.setServerInfo("nav.cn.ronghub.com", "up.qbox.me");
            RongIM.init(this);

            Thread.setDefaultUncaughtExceptionHandler(new RongExceptionHandler(this));



        }


    }
}
