package com.heshi.niuniu.util;

import com.heshi.niuniu.base.MyApplication;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.assit.WhereBuilder;
import com.litesuits.orm.db.model.ColumnsValue;
import com.litesuits.orm.db.model.ConflictAlgorithm;

import java.util.ArrayList;


/**
 * Author : Haily
 * Date   : ${DATE}${TIME}
 * Email  : zhanghailei55@gmail.com
 * Desc   :
 * Comment: //TODO
 */


public class DaoUtils {
    /**
     * 保存
     * @param
     */
    public static void save(final Object obj) {
        long save = MyApplication.liteOrm.save(obj);
        MyLog.e("DaoUtils", save + "");
    }

    /**
     * 根据条件更新数据
     * @param clazz
     * @param keyType
     * @param keyTime
     * @param valueType
     * @param valueTime
     * @param key
     * @param value
     */
    public static void undateByWhere(Class clazz, final String keyType, final String keyTime, final String valueType, final String valueTime, String[] key, Object[] value) {
        WhereBuilder where = new WhereBuilder(clazz).equals(keyType, valueType).andEquals(keyTime, valueTime);
        //更新的内容
        ColumnsValue cv = new ColumnsValue(key, value);
        MyApplication.liteOrm.update(where, cv, ConflictAlgorithm.None);
    }

    /**
     * 根据条件更新数据
     * @param clazz
     * @param
     * @param keyTime
     * @param
     * @param valueTime
     * @param key
     * @param value
     */
    public static void undateByWhere(Class clazz,  final String keyTime,  final String valueTime, String[] key, Object[] value) {
        WhereBuilder where = new WhereBuilder(clazz).equals(keyTime, keyTime);
        //更新的内容
        ColumnsValue cv = new ColumnsValue(key, value);
        MyApplication.liteOrm.update(where, cv, ConflictAlgorithm.None);
    }

    /**
     * 根据条件删除数据
     * @param clazz
     * @param keyType
     * @param keyTime
     * @param valueType
     * @param valueTime
     */
    public static void deleteLogByWhere(final Class clazz, final String keyType, final String keyTime, final String valueType, final String valueTime) {

        MyApplication.liteOrm.delete(WhereBuilder.create(clazz).equals(keyType, valueType).andEquals(keyTime, valueTime));
    }

    /**
     * 根据条件删除数据
     * @param clazz
     * @param
     * @param keyTime
     * @param
     * @param valueTime
     */
    public static void deleteLogByWhere(final Class clazz, final String keyTime, final String valueTime) {

        MyApplication.liteOrm.delete(WhereBuilder.create(clazz).equals(keyTime, valueTime));
    }

    /**
     * 全部删除
     * @param clazz
     */
    public void deleteAll(Class clazz) {
        MyApplication.liteOrm.deleteAll(clazz);
    }

    /**
     * 根据条件查询数据
     * @param valueType
     * @param valueTime
     * @return
     */
    public static synchronized <T> T quaryLogByWhere(Class<T> clazz, String valueType, String valueTime, String keyType, String keyTime) {     //HomeNewsListModel.RowsBean
        T logDao = null;
        QueryBuilder<T> newsCache = new QueryBuilder<>(clazz)
                .whereEquals(keyType, valueType)
                .whereAppendAnd()
                .whereEquals(keyTime, valueTime);

        ArrayList<T> query = MyApplication.liteOrm.query(newsCache);
        if (query != null && query.size() > 0) {
            logDao = query.get(0);
        }
        return logDao;
    }

    /**
     * 根据条件查询数据
     * @param
     * @param valueTime
     * @return
     */
    public static synchronized <T> T quaryLogByWhere(Class<T> clazz,  String valueTime, String keyTime) {     //HomeNewsListModel.RowsBean
        T logDao = null;
        QueryBuilder<T> newsCache = new QueryBuilder<>(clazz)

                .whereEquals(keyTime, valueTime);

        ArrayList<T> query = MyApplication.liteOrm.query(newsCache);
        if (query != null && query.size() > 0) {
            logDao = query.get(0);
        }
        return logDao;
    }

}
