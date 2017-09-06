package com.td.baseapp.base;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

import com.td.baselibs.MyApplication;
import com.td.baselibs.bean.UserBean;
import com.td.baselibs.utils.SharePreferenceUtil;


/**
 *
 */
public abstract class BaseFragment extends Fragment {
    protected BaseFragmentActivity mBaseActivity = null;
    protected Activity mActivity = null;
    protected String userId;
    protected UserBean userBean;
    protected SharePreferenceUtil spUilts;

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof BaseFragmentActivity) {
            this.mBaseActivity = (BaseFragmentActivity) activity;
        }
        this.mActivity = activity;
        spUilts = MyApplication.getInstance().mPreferenceUtil;
        userId = spUilts.getUserId();
        userBean = spUilts.getUser();
        Log.e("===", "onAcct");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    protected abstract void init(View view);

    /**
     * Activity跳转
     *
     * @param cls
     */
    public void startActivity(Class<?> cls) {
        Intent intent = new Intent(mActivity, cls);
        startActivity(intent);
    }


}
