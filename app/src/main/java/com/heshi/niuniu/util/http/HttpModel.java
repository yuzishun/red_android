package com.heshi.niuniu.util.http;

import java.util.LinkedHashMap;

/**
 * Name: HttpModel
 * Author: FrameJack
 * Email: framejackname@gmail.com
 * Date: 2018-01-29 15:49
 * Desc:
 * Comment: //TODO
 */
public class HttpModel {
    private LinkedHashMap<String, String> strList;
    private LinkedHashMap<String, Integer> intList;
    private LinkedHashMap<String, Float> floatList;
    private LinkedHashMap<String, Double> doubleList;

    public HttpModel(LinkedHashMap<String, String> strList, LinkedHashMap<String, Integer> intList) {
        this.strList = strList;
        this.intList = intList;
    }

    public HttpModel(LinkedHashMap<String, String> strList, LinkedHashMap<String, Integer> intList, LinkedHashMap<String, Float> floatList) {
        this.strList = strList;
        this.intList = intList;
        this.floatList = floatList;
    }

    public HttpModel(LinkedHashMap<String, String> strList, LinkedHashMap<String, Integer> intList, LinkedHashMap<String, Float> floatList, LinkedHashMap<String, Double> doubleList) {
        this.strList = strList;
        this.intList = intList;
        this.floatList = floatList;
        this.doubleList = doubleList;
    }

    public LinkedHashMap<String, Float> getFloatList() {
        return floatList;
    }

    public void setFloatList(LinkedHashMap<String, Float> floatList) {
        this.floatList = floatList;
    }

    public LinkedHashMap<String, Double> getDoubleList() {
        return doubleList;
    }

    public void setDoubleList(LinkedHashMap<String, Double> doubleList) {
        this.doubleList = doubleList;
    }

    public LinkedHashMap<String, String> getStrList() {
        return strList;
    }

    public void setStrList(LinkedHashMap<String, String> strList) {
        this.strList = strList;
    }

    public LinkedHashMap<String, Integer> getIntList() {
        return intList;
    }

    public void setIntList(LinkedHashMap<String, Integer> intList) {
        this.intList = intList;
    }
}
