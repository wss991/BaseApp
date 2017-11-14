package com.td.baseapp.ui.mvp;

import android.content.Context;

import mvp.BasePresenter;
import mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MvpContract {

    interface View extends BaseView {
        
    }

    interface  Presenter extends BasePresenter<View> {
        
    }
}
