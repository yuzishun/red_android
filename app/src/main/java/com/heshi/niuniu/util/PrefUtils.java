package com.heshi.niuniu.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.List;

/**
 * Created by Administrator on 2017/1/13 0013.
 */

public class PrefUtils {
    public static final String PREE_NAME = "config";

    public static void putBoolean(Context con, String key, boolean value) {
        SharedPreferences sp = con.getSharedPreferences(PREE_NAME, con.MODE_PRIVATE);
        sp.edit().putBoolean(key, value).commit();
    }

    public static boolean getBoolean(Context con, String key, boolean defvalue) {
        SharedPreferences sp = con.getSharedPreferences(PREE_NAME, con.MODE_PRIVATE);
        return sp.getBoolean(key, defvalue);
    }

    public static void putString(Context con, String key, String value) {
        SharedPreferences sp = con.getSharedPreferences(PREE_NAME, con.MODE_PRIVATE);
        sp.edit().putString(key, value).commit();
    }

    public static String getString(Context con, String key, String defvalue) {
        SharedPreferences sp = con.getSharedPreferences(PREE_NAME, con.MODE_PRIVATE);
        return sp.getString(key, defvalue);
    }

    public static void putInt(Context con, String key, int value) {
        SharedPreferences sp = con.getSharedPreferences(PREE_NAME, con.MODE_PRIVATE);
        sp.edit().putInt(key, value).commit();
    }

    public static int getInt(Context con, String key, int defvalue) {
        SharedPreferences sp = con.getSharedPreferences(PREE_NAME, con.MODE_PRIVATE);
        return sp.getInt(key, defvalue);
    }

    public static void removeKey(Context con, String key) {
        SharedPreferences sp = con.getSharedPreferences(PREE_NAME, con.MODE_PRIVATE);
        sp.edit().remove(key).commit();
    }

    public static <T> void putList(Context context, String filename, String key, List<T> list) {
        SharedPreferences mySharedPreferences = context.getSharedPreferences(filename,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = mySharedPreferences.edit();
        try {
            String liststr = ListToString(list);
            edit.putString(key, liststr);
            edit.commit();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public static <T> List<T> getList(Context context, String filename, String key) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(filename, Context.MODE_PRIVATE);
        String liststr = sharedPreferences.getString(key, "");
        try {
            List<T> list = StringToList(liststr);
            return list;
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static <T> String ListToString(List<T> list)
            throws IOException {
        //创建ByteArrayOutputStream对象，用来存放字节文件。
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        //得到的字符放到到ObjectOutputStream
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                byteArrayOutputStream);
        // writeObject 方法负责写入特定类的对象的状态，以便相应的 readObject 方法可以还原它
        objectOutputStream.writeObject(list);
        //Base64.encode将字节文件转换成Base64编码存在String中
        String string = new String(Base64.encode(
                byteArrayOutputStream.toByteArray(), Base64.DEFAULT));
        // 关闭Stream
        objectOutputStream.close();
        byteArrayOutputStream.close();
        return string;

    }

    @SuppressWarnings("unchecked")
    public static List StringToList(String string)
            throws StreamCorruptedException, IOException,
            ClassNotFoundException {
        byte[] b = Base64.decode(string.getBytes(),
                Base64.DEFAULT);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
                b);
        ObjectInputStream objectInputStream = new ObjectInputStream(
                byteArrayInputStream);
        List list = (List) objectInputStream
                .readObject();
        // 关闭Stream
        objectInputStream.close();
        byteArrayInputStream.close();
        return list;
    }

}
