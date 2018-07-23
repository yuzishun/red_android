package com.heshi.niuniu.util;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * RetrofitFileBuidler构造一个文件上传的body
 * Created by Min on 2017/4/29.
 */

public class RetrofitFileBuidler {
    protected Map<String, String> strParam;
    protected Map<String, File> fileParam;
    private MediaType mediaType = MediaType.parse("application/otcet-stream");
    private MediaType mediaTypeByMulti = MediaType.parse("multipart/form-data");

    private RetrofitFileBuidler() {
        strParam = new HashMap<>();
        fileParam = new HashMap<>();
    }

    public static RetrofitFileBuidler create() {
        return new RetrofitFileBuidler();
    }

    public RetrofitFileBuidler addStrParam(String key, String value) {
        strParam.put(key, value);
        return this;
    }
    public RetrofitFileBuidler addStrParam(String key, double value) {
        strParam.put(key, value+"");
        return this;
    }

    public RetrofitFileBuidler addFileParam(String key, File value) {
        fileParam.put(key, value);
        return this;
    }

    public Map<String, RequestBody> BuidlerMaps() {
        Map<String, RequestBody> result = new HashMap<>();
        /**
         * 迭代String的参数到构造者对象中
         */
        Iterator<Map.Entry<String, String>> strIter = strParam.entrySet().iterator();
        while (strIter.hasNext()) {
            Map.Entry<String, String> strItem = strIter.next();
            result.put(strItem.getKey(), RequestBody.create(MediaType.parse("text/plain"), strItem.getValue()));
        }
        /**
         * 迭代File的参数到构造者对象中
         */
        Iterator<Map.Entry<String, File>> fileIter = fileParam.entrySet().iterator();
        while (fileIter.hasNext()) {
            Map.Entry<String, File> fileItem = fileIter.next();
            File file = fileItem.getValue();
            result.put(fileItem.getKey()+"\";filename=\""+file.getName(), RequestBody.create(mediaTypeByMulti, file));
        }
        return result;
    }

    /**
     * 构造
     *
     * @return
     */
    public RequestBody Buidler() {
        MultipartBody.Builder requestBodyCache = new MultipartBody.Builder().setType(MultipartBody.FORM);

        /**
         * 迭代String的参数到构造者对象中
         */
        Iterator<Map.Entry<String, String>> strIter = strParam.entrySet().iterator();
        while (strIter.hasNext()) {
            Map.Entry<String, String> strItem = strIter.next();
            requestBodyCache.addFormDataPart(strItem.getKey(), strItem.getValue());
        }
        /**
         * 迭代File的参数到构造者对象中
         */
        Iterator<Map.Entry<String, File>> fileIter = fileParam.entrySet().iterator();
        while (fileIter.hasNext()) {
            Map.Entry<String, File> fileItem = fileIter.next();
            File file = fileItem.getValue();
            requestBodyCache.addFormDataPart(fileItem.getKey(), file.getName(),
                    RequestBody.create(mediaType, file));
        }
        return requestBodyCache.build();
    }
}
