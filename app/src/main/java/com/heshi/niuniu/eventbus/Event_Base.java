package com.heshi.niuniu.eventbus;

import java.io.Serializable;

/**
 * 通用的消息基类
 */

public class Event_Base<T extends Event_Base> implements Serializable {
    public Object obj;

    public int what = -1;

    public EType action = EType.Defalut;

    public T Action(EType action) {
        this.action = action;
        return (T) this;
    }

    public T What(int what) {
        this.what = what;
        return (T) this;
    }

    public T Obj(Object obj) {
        this.obj = obj;
        return (T) this;
    }

    /**
     * 判断obj是否为空
     *
     * @return
     */
    public boolean objIsNotNull() {
        return obj != null;
    }

    /**
     * 判断obj是否允许转换为某个class
     * 也可作为判断obj的对象内容是否为某个class的子类或本身
     *
     * @param cls
     * @return
     */
    public boolean objIsShiftClass(Class cls) {
        if (objIsNotNull()) {
            Class objClass = obj.getClass();
            return objClass.isAssignableFrom(cls);
        }
        return false;
    }

    /**
     * 通用的Action枚举
     */
    public enum EType {
        SET_PHOTO, Defalut
    }
}
