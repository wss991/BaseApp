package com.td.baseapp.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lzy.okgo.OkGo;
import com.td.baselibs.MyApplication;
import com.td.baselibs.bean.UserBean;
import com.td.baselibs.utils.SharePreferenceUtil;


public abstract class BaseFragmentActivity extends AppCompatActivity {

    public Context mContext;   //吐司的上下文
    protected Context AContext;
    public String userId;
    protected UserBean userBean;
    protected SharePreferenceUtil spUilts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        AContext = getApplicationContext();
        spUilts = MyApplication.getInstance().mPreferenceUtil;
        userId = spUilts.getUserId();
        userBean = spUilts.getUser();
      /*  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }*/
        initView();
        getData();
    }


    protected abstract void getData();

    public abstract void initView();

    @Override
    protected void onDestroy() {
        OkGo.getInstance().cancelTag(this);
        super.onDestroy();

    }


}
