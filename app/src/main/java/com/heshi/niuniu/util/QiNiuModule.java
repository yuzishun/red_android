package com.heshi.niuniu.util;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.module.GlideModule;

/**
 * Created by min on 2017/12/5.
 * 支持缓存动态URL图
 */

public class QiNiuModule implements GlideModule{
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {

    }

    @Override
    public void registerComponents(Context context, Glide glide) {
//        glide.register(QiNiuImage.class, InputStream.class, new QiNiuImageLoader.Factory());
    }
}
