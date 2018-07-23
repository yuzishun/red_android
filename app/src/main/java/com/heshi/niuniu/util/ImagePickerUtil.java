package com.heshi.niuniu.util;


import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.view.CropImageView;

/**
 * Created by min on 2017/7/25.
 */

public class ImagePickerUtil {

    /**
     * 实例化
     */
    public static void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setMultiMode(false);
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);                      //显示拍照按钮
        imagePicker.setCrop(false);                           //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true);                   //是否按矩形区域保存
        //        imagePicker.setSelectLimit(maxImgCount);              //选中数量限制
        //        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        //        imagePicker.setFocusWidth(1200);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        //        imagePicker.setFocusHeight(1500);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        //        imagePicker.setOutPutX(3000);                         //保存文件的宽度。单位像素
        //        imagePicker.setOutPutY(4000);                         //保存文件的高度。单位像素
        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);                         //保存文件的高度。单位像素
   

}

    /**
     * 实例化
     */
    public static void initImageMorePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setMultiMode(true);
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);                      //显示拍照按钮
        imagePicker.setCrop(false);                           //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true);                   //是否按矩形区域保存
        //        imagePicker.setSelectLimit(maxImgCount);              //选中数量限制
        //        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        //        imagePicker.setFocusWidth(1200);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        //        imagePicker.setFocusHeight(1500);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        //        imagePicker.setOutPutX(3000);                         //保存文件的宽度。单位像素
        //        imagePicker.setOutPutY(4000);                         //保存文件的高度。单位像素
        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);                         //保存文件的高度。单位像素


    }

    /**
     * 实例化
     */
    public static void initImagePicker(int maxImgCount) {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setMultiMode(true);
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);                      //显示拍照按钮
        imagePicker.setCrop(false);                           //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true);                   //是否按矩形区域保存
        imagePicker.setSelectLimit(maxImgCount);              //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        /*imagePicker.setFocusWidth(1200);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(1500);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(3000);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(4000);     */                    //保存文件的高度。单位像素
    }


}
