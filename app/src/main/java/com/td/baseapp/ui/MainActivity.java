package com.td.baseapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.lzy.okgo.OkGo;

import com.lzy.okgo.model.HttpParams;
import com.td.baseapp.HttpManager;
import com.td.baseapp.HttpManagerRx;
import com.td.baseapp.R;;
import com.td.baselibs.bean.UserBean;
import com.td.baselibs.http.callbak.OnResultCallBack;
import com.td.baselibs.http.callbak.JsonCallback;
import com.td.baselibs.http.model.ApiResponse;
import com.td.baselibs.http.model.NetWorkAPI;
import com.td.baselibs.http.subscriber.HttpSubscriber;

import java.io.IOException;

import io.reactivex.disposables.Disposable;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okio.BufferedSink;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/*
        OkGo.<ApiResponse<UserBean>>post(NetWorkAPI.REFRESH_USER_AIP)//
                .tag(this)//
                .params("userId", "1")//
                .execute(new JsonCallback<>(new OnResultCallBack<ApiResponse<UserBean>>() {

                    @Override
                    public void onSuccess(ApiResponse<UserBean> userBeanApiResponse) {
                        Log.e("===", userBeanApiResponse.data.toString() + "");
                    }

                    @Override
                    public void onError(int code, String errorMsg) {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                }));*/
        // OkGo.cancelTag(this);

        HttpParams params = new HttpParams();
        params.put("userId", "1902");
        HttpManagerRx.getInstance().refreshUser(this, params, new HttpSubscriber<UserBean>(new OnResultCallBack<UserBean>() {

            @Override
            public void onSuccess(UserBean userBean) {

                Log.e("===",userBean.toString());
            }

            @Override
            public void onError(int code, String errorMsg) {

            }

            @Override
            public void onSubscribe(Disposable d) {

            }


        }));

    /*    HttpManager.getInstance().refreshUser(this, params, new OnResultCallBack<ApiResponse<UserBean>>() {
            @Override
            public void onSuccess(ApiResponse<UserBean> userBeanApiResponse) {
                Log.e("===",userBeanApiResponse.data.toString());
            }

            @Override
            public void onError(int code, String errorMsg) {

            }

            @Override
            public void onSubscribe(Disposable d) {

            }
        });*/
    }
}

