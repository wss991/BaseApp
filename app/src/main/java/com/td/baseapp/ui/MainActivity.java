package com.td.baseapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.lzy.okgo.OkGo;

import com.td.baseapp.R;;
import com.td.baselibs.bean.UserBean;
import com.td.baselibs.http.callbak.OnResultCallBack;
import com.td.baselibs.http.callbak.JsonCallback;
import com.td.baselibs.http.model.ApiResponse;
import com.td.baselibs.http.model.NetWorkAPI;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okio.BufferedSink;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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
                }));
        // OkGo.cancelTag(this);
    }
}

