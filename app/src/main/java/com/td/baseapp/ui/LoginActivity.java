package com.td.baseapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.td.baseapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        OkGo
                .<String>post("http://api.sealtalk.im/user/login ")
                .upJson("{\"password\":\"123456789\",\"phone\":\"13051261914\",\"region\":\"86\"}")
                .execute(new StringCallback(){

                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.e("===",response.body().toString());
                        Gson gson=new Gson();
                        try {
                            JSONObject object = new JSONObject(response.body().toString());
                            JSONObject obj= object.getJSONObject("result");
                            String token= obj.getString("token");
                            RongIM.connect(token, new RongIMClient.ConnectCallback() {
                                @Override
                                public void onTokenIncorrect() {
                                    Log.e("===", "reToken Incorrect");
                                }

                                @Override
                                public void onSuccess(String s) {
                                    Log.e("===", "st");
                                }

                                @Override
                                public void onError(RongIMClient.ErrorCode e) {
                                    Log.e("===", e+"          ");
                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
    }
}
