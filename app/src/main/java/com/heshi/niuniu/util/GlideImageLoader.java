package com.heshi.niuniu.util;

import android.app.Activity;
import android.os.Environment;
import android.text.TextUtils;
import android.widget.ImageView;

import com.lzy.imagepicker.loader.ImageLoader;

/**
 * ================================================
 * 作    者：jeasonlzy（廖子尧 Github地址：https://github.com/jeasonlzy0216
 * 版    本：1.0
 * 创建日期：2016/5/19
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class GlideImageLoader implements ImageLoader {

    @Override
    public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
        String externalStorageDirectory = Environment.getExternalStorageDirectory().getAbsolutePath();
        String dataDirectory = Environment.getDataDirectory().getAbsolutePath();
        if (!TextUtils.isEmpty(path)) {
            if (path.contains(externalStorageDirectory) || path.contains(dataDirectory)) {
                GlideUtils.loadRoundTransformCacheUri(path, imageView, true);
            } else {
                GlideUtils.loadRoundTransformCacheUri(path, imageView, false);

            }
        }


    }

    @Override
    public void displayImagePreview(Activity activity, String path, ImageView imageView, int width, int height) {
        String externalStorageDirectory = Environment.getExternalStorageDirectory().getAbsolutePath();
        String dataDirectory = Environment.getDataDirectory().getAbsolutePath();
        if (!TextUtils.isEmpty(path)) {
            if (path.contains(externalStorageDirectory) || path.contains(dataDirectory)) {
                GlideUtils.loadCacheUri(path, imageView);
            } else {
                GlideUtils.loadImg(path, imageView);
            }
        }


    }

    @Override
    public void clearMemoryCache() {
    }
}
