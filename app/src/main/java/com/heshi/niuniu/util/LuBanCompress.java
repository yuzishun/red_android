package com.heshi.niuniu.util;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.File;

import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;


/**
 * Created by min on 2017/7/26.
 *  鲁班压缩
 */

public class LuBanCompress {

//    private void compressWithRx(File file,final Activity activity,final ImageView image) {
//        Flowable.just(file)
//                .observeOn(Schedulers.io())
//                .map(new Function<File, File>() {
//                    @Override public File apply(@NonNull File file) throws Exception {
//                        return Luban.with(activity).load(file).get();
//                    }
//                })
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<File>() {
//                    @Override public void accept(@NonNull File file) throws Exception {
//                        Log.d(TAG, file.getAbsolutePath());
//
//                        Glide.with(activity).load(file).into(image);
//                        ToashUtils.show(activity,"原图大小"+file.length() / 1024 + "k");
//                        ToashUtils.show(activity,"压缩后"+computeSize(file)[0] + "*" + computeSize(file)[1]);
////                        thumbFileSize.setText(file.length() / 1024 + "k");
////                        thumbImageSize.setText(computeSize(file)[0] + "*" + computeSize(file)[1]);
//                    }
//                });
//    }

    /**
     * 压缩单张图片 Listener 方式
     */
    public static File compressWithLs(File file, final Activity activity ) {//final ImageView image
        final File[] myFile = new File[1];
        Luban.with(activity)
                .load(file)
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {
//                        Toast.makeText(activity, "I'm start", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(File file) {
                        myFile[0] =file;
                        Log.i("path", file.getAbsolutePath());

//                        Glide.with(activity).load(file).into(image);

                        Log.e("压缩后",file.length() / 1024 + "k");
                        Log.e("像素：",computeSize(file)[0] + "*" + computeSize(file)[1]);
//                        thumbFileSize.setText(file.length() / 1024 + "k");
//                        thumbImageSize.setText(computeSize(file)[0] + "*" + computeSize(file)[1]);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }).launch();
        return  file;
    }

    private static  int[] computeSize(File srcImg) {
        int[] size = new int[2];

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inSampleSize = 1;

        BitmapFactory.decodeFile(srcImg.getAbsolutePath(), options);
        size[0] = options.outWidth;
        size[1] = options.outHeight;

        return size;
    }
}
