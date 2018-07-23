package com.heshi.niuniu.util.dialogutil;

import java.io.Serializable;

/**
 * Name: DialogSetParameter
 * Author: FrameJack
 * Email: framejackname@gmail.com
 * Date: 2017-10-12 09:25
 * Desc:
 * Comment: //TODO
 */
public class DialogSetParameter implements Serializable {

    private int width;//宽
    private int height;//高
    private int curPosition;//显示的位置

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getCurPosition() {
        return curPosition;
    }

    public void setCurPosition(int curPosition) {
        this.curPosition = curPosition;
    }
}
