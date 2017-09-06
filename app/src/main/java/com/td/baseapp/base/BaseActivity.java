package com.td.baseapp.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.lzy.okgo.OkGo;
import com.td.baselibs.bean.UserBean;
import com.td.baselibs.utils.SharePreferenceUtil;


public abstract class BaseActivity extends FragmentActivity {
    public Context mContext;   //吐司的上下文
    protected Context AContext;
    protected UserBean userBean;
    protected SharePreferenceUtil spUilts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        AContext = getApplicationContext();

    /*    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
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