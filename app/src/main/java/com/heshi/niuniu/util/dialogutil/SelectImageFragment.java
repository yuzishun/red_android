package com.heshi.niuniu.util.dialogutil;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.heshi.niuniu.R;
import com.heshi.niuniu.di.component.AppComponent;
import com.heshi.niuniu.di.module.FragmentModule;
import com.heshi.niuniu.util.TimeUtil;
import com.heshi.niuniu.util.ToashUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * Name: SelectImageFragment
 * Author: FrameJack
 * Email: framejackname@gmail.com
 * Date: 2017-07-24 10:24
 * Desc:图片选择（拍照）
 * Comment: 选择图片DialogFragment 也只能这样写了
 * //TODO
 */
public class SelectImageFragment extends BaseDialogFragment {

    /**
     * 头像名称
     */
    private static final String IMAGE_FILE_NAME = "image.jpg";
    /**
     * 请求码
     */
    private static final int IMAGE_REQUEST_CODE = 0;
    private static final int CAMERA_REQUEST_CODE = 1;
    private static final int RESULT_REQUEST_CODE = 2;
    private static final int NO_CROP = 3;
    private ImageView imgUserdataHead;
    private selectCompletionListener listener;
    private Context mContext;
    private boolean isCrop = true;
    private int type;//1 拍照 0 相册选取

    @Override
    protected void setupActivityComponent(AppComponent appComponent, FragmentModule fragmentModule) {

    }

    public interface selectCompletionListener {
        void completion(String path);
    }

    /**
     * @param data
     * @param imgUserdataHead 选择后显示图片的控件
     * @param listener        回调
     * @return
     */
    public static SelectImageFragment getInstance(Bundle data, ImageView imgUserdataHead
            , selectCompletionListener listener) {
        SelectImageFragment fragment = new SelectImageFragment();
        fragment.setArguments(data);
        fragment.listener = listener;
        if (imgUserdataHead != null) {
            fragment.imgUserdataHead = imgUserdataHead;
        }
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_select_image;
    }

    @Override
    protected void initData() {
        super.initData();
        isCrop = getArguments().getBoolean("isCrop");

        TextView takePhone = (TextView) view.findViewById(R.id.text_select_take_phone);
        TextView selectPhone = (TextView) view.findViewById(R.id.text_select_take_select);
        TextView cancel = (TextView) view.findViewById(R.id.text_select_cancel);

        takePhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doPhone();
            }
        });
        selectPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != 0) {
            switch (requestCode) {
                case IMAGE_REQUEST_CODE:
                    startPhotoZoom(data.getData());

                    break;
                case CAMERA_REQUEST_CODE:
                    // 判断存储卡是否可以用，可用进行存储
                    String state = Environment.getExternalStorageState();
                    if (state.equals(Environment.MEDIA_MOUNTED)) {
                        File path = Environment
                                .getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
                        File tempFile = new File(path, IMAGE_FILE_NAME);
                        startPhotoZoom(Uri.fromFile(tempFile));

                    } else {
                        ToashUtils.show(mContext, getString(R.string.the_memory_card_was_not_found));
                    }

                    break;
                case RESULT_REQUEST_CODE: // 图片缩放完成后
                    if (data != null) {
                        getImageToView(data);
                    }
                    break;
                case NO_CROP:
                    Uri uri;
                    if (type == IMAGE_REQUEST_CODE) {
                        uri = data.getData();
                    } else {
                        File path = Environment
                                .getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
                        File tempFile = new File(path, IMAGE_FILE_NAME);
                        uri = Uri.fromFile(tempFile);
                        Log.e("uri", uri.toString());
                    }
                    noCropAction(uri);

                    break;
            }
        }
    }

    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 340);
        intent.putExtra("outputY", 340);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, RESULT_REQUEST_CODE);
    }

    public void noCropAction(Uri uri) {
        ContentResolver cr = mContext.getContentResolver();
        try {
            Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
            if (imgUserdataHead != null) {
                              /* 将Bitmap设定到ImageView */
                imgUserdataHead.setImageBitmap(bitmap);
            }
            String imgPath = saveCroppedImage(bitmap);
            if (listener != null) {
                listener.completion(imgPath);
                dismiss();
            }

        } catch (FileNotFoundException e) {
            Log.e("Exception", e.getMessage(), e);
        }
    }


    //拍照
    private void doPhone() {
        int code;
        if (isCrop) {
            code = CAMERA_REQUEST_CODE;
        } else {
            code = NO_CROP;
            type = CAMERA_REQUEST_CODE;
        }

        Intent intentFromCapture = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE);
        // 判断存储卡是否可以用，可用进行存储
        String state = Environment
                .getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            File path = Environment
                    .getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
            File file = new File(path, IMAGE_FILE_NAME);
            intentFromCapture.putExtra(
                    MediaStore.EXTRA_OUTPUT,
                    Uri.fromFile(file));
        }

        startActivityForResult(intentFromCapture,
                code);
    }

    //系统相册
    private void selectImage() {
        int code;
        if (isCrop) {
            code = IMAGE_REQUEST_CODE;
        } else {
            code = NO_CROP;
            type = IMAGE_REQUEST_CODE;
        }
        Intent intentFromGallery = new Intent();
        intentFromGallery.setType("image/*"); // 设置文件类型
        intentFromGallery
                .setAction(Intent.ACTION_PICK);
        startActivityForResult(intentFromGallery,
                code);
    }

    /**
     * 保存裁剪之后的图片数据
     *
     * @param
     */
    private void getImageToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            Drawable drawable = new BitmapDrawable(this.getResources(), photo);
            if (imgUserdataHead != null) {
                imgUserdataHead.setImageDrawable(drawable);
            }
            String path = saveCroppedImage(photo);
            if (listener != null) {
                listener.completion(path);
                dismiss();
            }
            //上传图片
//            okHttpUpLoadingImageUtil(path);
        }
    }

    /***
     * 保存bitmap到本地
     */
    private String saveCroppedImage(Bitmap bmp) {
        File file = new File("/sdcard/myFolder");
        if (!file.exists())
            file.mkdir();

        file = new File("/sdcard/temp.jpg".trim());
        String fileName = file.getName();
        String mName = fileName.substring(0, fileName.lastIndexOf("."));
        String sName = fileName.substring(fileName.lastIndexOf("."));
        Random random = new Random();
        int str = random.nextInt(100);

        TimeUtil util = new TimeUtil();
        String data = util.getCurTime() + str;

        // /sdcard/myFolder/temp_cropped.jpg
        String newFilePath = "/sdcard/myFolder" + "/" + mName +"_"+ data + sName;
        file = new File(newFilePath);
        try {
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 50, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return newFilePath;
    }
}
