
package com.td.baseapp.db;

import android.content.Context;

import com.td.baseapp.MyApplication;
import com.td.baseapp.gen.DaoMaster;
import com.td.baseapp.gen.DaoSession;

public class GreenDaoManager {
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;




    /**
     * 静态内部类，实例化对象使用
     */

    private static class SingleInstanceHolder {
        private static final GreenDaoManager INSTANCE = new GreenDaoManager();
    }


    /**
     * 对外唯一实例的接口
     *
     * @return
     */

    public static GreenDaoManager getInstance() {
        return SingleInstanceHolder.INSTANCE;
    }


    /**
     * 初始化数据
     */

    public void init(Context context) {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context,
                "basedb");
        mDaoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        mDaoSession = mDaoMaster.newSession();
    }

    public DaoMaster getmDaoMaster() {
        return mDaoMaster;
    }

    public DaoSession getmDaoSession() {
        return mDaoSession;
    }

    public DaoSession getNewSession() {
        mDaoSession = mDaoMaster.newSession();
        return mDaoSession;
    }
}


