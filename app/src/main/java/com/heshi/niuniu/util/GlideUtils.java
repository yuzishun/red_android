package com.heshi.niuniu.util;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.signature.StringSignature;
import com.heshi.niuniu.R;
import com.heshi.niuniu.base.MyApplication;
import com.heshi.niuniu.custom.photoview.PhotoView;
import com.heshi.niuniu.util.glideutil.GlideRoundTransform;


import java.io.File;


/**
 * Created by wali on 2016/9/22.
 */
public class GlideUtils {
    public static void loadImg(String url, ImageView imageView) {
        Glide.with(MyApplication.application)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .error(R.mipmap.placeholder)
                .into(imageView);
    }

    public static void noCacheloadImg(String url, ImageView imageView) {
        Glide.with(MyApplication.application)
                .load(url)
//                .error(R.mipmap.icon_img_error)
//                .placeholder(R.mipmap.icon_img_error)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(imageView);
    }

    public static void getPersonCache(String url, ImageView imageView, String signature) {
        Glide.with(MyApplication.application)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .priority(Priority.NORMAL)
//                .error(R.mipmap.peresonal_photo)
                .signature(new StringSignature(signature)) //   给个标签来判断是否加载缓存
                .into(imageView);

    }

    public static void loadImg(String url, ImageView imageView, int imgId) {
        Glide.with(MyApplication.application)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(imgId)
                .into(imageView);
    }

    public static void loadImg(Context context, String url, ImageView imageView, int imgId, boolean isAddListener) {
        DrawableRequestBuilder builder;
        builder = Glide.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(imgId)
                .error(imgId);

        if (!isAddListener) {
            builder.into(imageView);

        } else {
            final HttpDialog dialog = new HttpDialog(context);
            dialog.show();

            builder.listener(new RequestListener() {
                @Override
                public boolean onException(Exception e, Object o, Target target, boolean b) {
//                    Toast.makeText(getApplicationContext(),"资源加载异常",Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    return false;
                }

                @Override
                public boolean onResourceReady(Object o, Object o2, Target target, boolean b, boolean b1) {
//                    Toast.makeText(getApplicationContext(),"图片加载完成",Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    return false;
                }
            }).into(imageView);
        }

    }

    public static void loadImg(int url, ImageView imageView) {
        Glide.with(MyApplication.application)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);

    }


    public static void loadImgP(String url, final PhotoView photoView) {
        Glide.with(MyApplication.application)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .error(R.mipmap.peresonal_photo)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        photoView.enable();
                        photoView.setEnabled(true);
                        photoView.setClickable(true);
                        return false;
                    }
                })
                //                .placeholder(R.mipmap.loading)
                .into(photoView);

    }

    public static void loadCacheUri(String path, int width, int height, ImageView imageView) {
        Glide.with(MyApplication.application)
                //.load(path)
                //配置上下文
                .load(Uri.fromFile(new File(path)))      //设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
//                .error(R.mipmap.placeholder)           //设置错误图片
//                .placeholder(R.mipmap.placeholder)     //设置占位图片
                .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸
                .override(width, height)
                .into(imageView);
    }

    public static void loadCacheUri(String path, ImageView imageView) {
        Glide.with(MyApplication.application)
                //.load(path)
                //配置上下文
                .load(Uri.fromFile(new File(path)))      //设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
//                .error(R.mipmap.placeholder)           //设置错误图片
//                .placeholder(R.mipmap.placeholder)     //设置占位图片
                .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸
                .into(imageView);
    }

    public static void loadCacheLocalUri(int path, ImageView imageView) {
        Glide.with(MyApplication.application)
                //.load(path)
                //配置上下文
                .load(path)      //设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
//                .error(R.mipmap.placeholder)           //设置错误图片
//                .placeholder(R.mipmap.placeholder)     //设置占位图片
                .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸
                .into(imageView);
    }

    public static void loadisPlaceholderImg(String url, ImageView imageView) {
        Glide.with(MyApplication.application)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .error(R.mipmap.placeholder)
//                .placeholder(R.mipmap.placeholder)
                .into(imageView);
    }

    public static void loadRoundTransformCacheUri(String path, ImageView imageView, boolean isLocal) {
        Object url;

        if (isLocal) {
            url = Uri.fromFile(new File(path));
        } else {
            url = path;
        }

        Glide.with(MyApplication.application)
                //.load(path)
                //配置上下文

                .load(url)      //设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .error(R.mipmap.placeholder)
//                .placeholder(R.mipmap.placeholder)
//                .transform(new GlideRoundTransform(MyApplication.application, 10))
                .into(imageView);
    }


}
