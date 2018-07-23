package com.heshi.niuniu.util.http;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Name: HttpParams
 * Author: FrameJack
 * Email: framejackname@gmail.com
 * Date: 2017-07-24 13:45
 * Desc: This class as a file upload return MultipartBody.Part
 * Comment: //TODO
 */
public class HttpParams implements Serializable {

    /**
     * 普通的键值对参数
     */
    public LinkedHashMap<String, List<String>> urlParamsMap;
    /**
     * 文件的键值对参数
     */
    public LinkedHashMap<String, List<File>> fileParamsMap;

    private LinkedHashMap<String, String> strOptions;
    private LinkedHashMap<String, Integer> intOptions;
    private LinkedHashMap<String, Float> floatOptions;
    private LinkedHashMap<String, Double> doubleOptions;


    public HttpParams() {
        init();
    }

    private void init() {
        urlParamsMap = new LinkedHashMap<>();
        fileParamsMap = new LinkedHashMap<>();
        strOptions = new LinkedHashMap<>();
        intOptions = new LinkedHashMap<>();
        floatOptions = new LinkedHashMap<>();
        doubleOptions = new LinkedHashMap<>();
    }

    /**
     * String
     *
     * @param key
     * @param value
     */
    public void put(String key, String value) {
        if (key != null && value != null) {
            List<String> urlValues = urlParamsMap.get(key);
            if (urlValues == null) {
                urlValues = new ArrayList<>();
                strOptions.put(key, value);
                urlParamsMap.put(key, urlValues);
            }
            urlValues.add(value);
        }
    }

    /**
     * file
     *
     * @param key
     * @param file
     */
    public void put(String key, File file) {
        if (key != null) {
            List<File> fileWrappers = fileParamsMap.get(key);
            if (fileWrappers == null) {
                fileWrappers = new ArrayList<>();
                fileParamsMap.put(key, fileWrappers);
            }
            fileWrappers.add(file);
        }

    }

    /**
     * Temporarily in less than
     *
     * @param params
     */
    public void put(HttpParams params) {
        if (params != null) {
            if (params.urlParamsMap != null && !params.urlParamsMap.isEmpty())
                urlParamsMap.putAll(params.urlParamsMap);
            if (params.fileParamsMap != null && !params.fileParamsMap.isEmpty())
                fileParamsMap.putAll(params.fileParamsMap);
        }
    }

    /**
     * Set the data returned
     *
     * @return
     */
    public List<MultipartBody.Part> setType() {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        for (ConcurrentHashMap.Entry<String, List<String>> entry : urlParamsMap.entrySet()) {
            builder.addFormDataPart(entry.getKey() + "", entry.getValue().get(0) + "");
        }

        builder.setType(MultipartBody.FORM);//表单类型

        for (ConcurrentHashMap.Entry<String, List<File>> entry : fileParamsMap.entrySet()) {
            if (entry.getValue().get(0) != null) {

                RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), entry.getValue().get(0));
                builder.addFormDataPart("mFile", entry.getValue().get(0).getName(), imageBody);//imgfile 后台接收图片流的参数名
            }
        }

        List<MultipartBody.Part> parts = builder.build().parts();
        return parts;
    }

    /**
     * Set the data returned
     *
     * @return
     */
    public List<MultipartBody.Part> setMoreImgType() {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        for (ConcurrentHashMap.Entry<String, List<String>> entry : urlParamsMap.entrySet()) {
            builder.addFormDataPart(entry.getKey() + "", entry.getValue().get(0) + "");
        }

        builder.setType(MultipartBody.FORM);//表单类型

        for (ConcurrentHashMap.Entry<String, List<File>> entry : fileParamsMap.entrySet()) {
            if (entry.getValue() != null) {
                List<File> list=entry.getValue();
                for (int i = 0; i <list.size() ; i++) {
                    RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), entry.getValue().get(i));
                    builder.addFormDataPart("mFile", entry.getValue().get(i).getName(), imageBody);//imgfile 后台接收图片流的参数名
                }
            }
        }

        List<MultipartBody.Part> parts = builder.build().parts();
        return parts;
    }

    public void put(String key, Object value) {
        if (key != null && value != null) {
            if (value instanceof Integer) {
                intOptions.put(key, (int) value);
            } else if (value instanceof Float) {
                floatOptions.put(key, (Float) value);
            } else if (value instanceof Double) {
                doubleOptions.put(key, (Double) value);
            }
        }

    }

    public HttpModel setMapType() {
        HttpModel model = new HttpModel(strOptions, intOptions, floatOptions, doubleOptions);
        return model;
    }

}
