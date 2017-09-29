package com.td.baseapp;

import android.app.Activity;
import android.app.Application;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.HttpParams;
import com.td.baselibs.bean.UserBean;
import com.td.baselibs.http.callbak.JsonCallback;
import com.td.baselibs.http.callbak.OnResultCallBack;
import com.td.baselibs.http.model.ApiResponse;
import com.td.baselibs.http.model.NetWorkAPI;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by my on 2017/9/28.
 */

public class HttpManager {

    private static HttpManager instance;

    public static void init(Application application) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);      //全局的读取超时时间
        builder.writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);     //全局的写入超时时间
        builder.connectTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);   //全局的连接超时时间

        // 其他统一的配置
        // 详细说明看GitHub文档：https://github.com/jeasonlzy/
        OkGo.getInstance().init(application)                           //必须调用初始化
                .setOkHttpClient(builder.build())               //建议设置OkHttpClient，不设置会使用默认的
                .setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
                .setRetryCount(0);                               //全局统一超时重连次数，默认为三次，那么最差的情况会请
    }

    public static HttpManager getInstance() {
        if (instance == null) {
            synchronized (HttpManager.class) {
                if (instance == null) {
                    instance = new HttpManager();
                }
            }
        }
        return instance;
    }

    public void login(Activity activity, HttpParams params,OnResultCallBack<ApiResponse<UserBean>> callBack) {
        OkGo.<ApiResponse<UserBean>>get(NetWorkAPI.LOGIN_API)
                .tag(activity)
                .params(params)
                .execute(new JsonCallback<>(callBack));
    }

    public void refreshUser(Activity activity, HttpParams params,OnResultCallBack<ApiResponse<UserBean>> callBack) {
        OkGo.<ApiResponse<UserBean>>post(NetWorkAPI.REFRESH_USER_AIP)
                .tag(activity)
                .params(params)
                .execute(new JsonCallback<>(callBack));
    }
}
