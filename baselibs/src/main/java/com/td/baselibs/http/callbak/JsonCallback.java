/*
 * Copyright 2016 jeasonlzy(廖子尧)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.td.baselibs.http.callbak;

import com.google.gson.stream.MalformedJsonException;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.request.base.Request;
import com.td.baselibs.http.exception.ApiException;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import okhttp3.Response;

/**
 * ================================================
 * 作    者：jeasonlzy（廖子尧）Github地址：https://github.com/jeasonlzy
 * 版    本：1.0
 * 创建日期：2016/1/14
 * 描    述：默认将返回的数据解析成需要的Bean,可以是 BaseBean，String，List，Map
 * 修订历史：
 * ================================================
 */
public  class JsonCallback<T> extends AbsCallback<T> {

    private Type type;
    private Class<T> clazz;
    private OnResultCallBack mOnResultListener;

    public JsonCallback(OnResultCallBack<T> mOnResultListener) {
        this.mOnResultListener = mOnResultListener;
    }

    public JsonCallback() {
    }

    public JsonCallback(Type type) {
        this.type = type;
    }

    public JsonCallback(Class<T> clazz) {
        this.clazz = clazz;
    }


    @Override
    public void onStart(Request<T, ? extends Request> request) {
        super.onStart(request);
       /* // 主要用于在所有请求之前添加公共的请求头或请求参数
        // 例如登录授权的 token
        // 使用的设备信息
        // 可以随意添加,也可以什么都不传
        // 还可以在这里对所有的参数进行加密，均在这里实现
        request.headers("header1", "HeaderValue1")//
                .params("params1", "ParamsValue1")//
                .params("token", "3215sdf13ad1f65asd4f3ads1f");*/
    }

    /**
     * 该方法是子线程处理，不能做ui相关的工作
     * 主要作用是解析网络返回的 response 对象,生产onSuccess回调中需要的数据对象
     * 这里的解析工作不同的业务逻辑基本都不一样,所以需要自己实现,以下给出的时模板代码,实际使用根据需要修改
     */
    @Override
    public T convertResponse(Response response) throws Throwable {

        // 重要的事情说三遍，不同的业务，这里的代码逻辑都不一样，如果你不修改，那么基本不可用
        // 重要的事情说三遍，不同的业务，这里的代码逻辑都不一样，如果你不修改，那么基本不可用
        // 重要的事情说三遍，不同的业务，这里的代码逻辑都不一样，如果你不修改，那么基本不可用

        //详细自定义的原理和文档，看这里： https://github.com/jeasonlzy/okhttp-OkGo/wiki/JsonCallback

        if (type == null) {
            if (clazz == null) {
                Type genType = OnResultCallBack.genType;
                type = ((ParameterizedType) genType).getActualTypeArguments()[0];
            } else {
                JsonConvert<T> convert = new JsonConvert<>(clazz);
                return convert.convertResponse(response);
            }
        }

        JsonConvert<T> convert = new JsonConvert<>(type);
        return convert.convertResponse(response);
    }

    @Override
    public void onSuccess(com.lzy.okgo.model.Response<T> response) {
        if(mOnResultListener!=null){
            mOnResultListener.onSuccess(response.body());
        }
    }

    @Override
    public void onError(com.lzy.okgo.model.Response<T> response) {
        Throwable e = response.getException();
        if (e != null) {
            if (e instanceof SocketTimeoutException) {
                mOnResultListener.onError(ApiException.Code_TimeOut, ApiException.SOCKET_TIMEOUT_EXCEPTION);
            } else if (e instanceof ConnectException) {
                mOnResultListener.onError(ApiException.Code_UnConnected, ApiException.CONNECT_EXCEPTION);
            } else if (e instanceof UnknownHostException) {
                mOnResultListener.onError(ApiException.Code_UnConnected, ApiException.CONNECT_EXCEPTION);
            } else if (e instanceof MalformedJsonException) {
                mOnResultListener.onError(ApiException.Code_MalformedJson, ApiException.MALFORMED_JSON_EXCEPTION);
            } else if (e instanceof ApiException) {

                String msg = e.getMessage();
                int code;
                if (msg.contains("#")) {
                    code = Integer.parseInt(msg.split("#")[0]);
                    mOnResultListener.onError(code, msg.split("#")[1]);
                } else {
                    code = ApiException.Code_Default;
                    mOnResultListener.onError(code, msg);
                }
            } else {
                mOnResultListener.onError(1001, "网络请求失败");
            }
        }
    }

}
