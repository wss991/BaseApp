package com.td.baselibs.utils;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by my on 2017/8/21.
 */

public class PicassoUtils {

    /**
     *  设置头像，根据项目需求 ，自己添加占位图 和错误图片
     * @param context
     * @param url
     * @param imageView
     */
    public static void setAvatorImage(Context context, String url, ImageView imageView) {
        Picasso.with(context).load(url).tag(context).into(imageView);

    }

    /**
     *  设置项目中，图片展示的
     * @param context
     * @param url
     * @param imageView
     */
    public static void setImage(Context context, String url, ImageView imageView) {
        Picasso.with(context).load(url).tag(context).into(imageView);

    }

    /**
     *  取消图片的请求
     * @param context
     */
    public static void canclePicasso(Context context) {
        Picasso.with(context).cancelTag(context);
    }
}
