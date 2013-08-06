package com.kelepi.common.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-7-7
 * Time: 下午3:38
 * To change this template use File | Settings | File Templates.
 */
public class Singleton {
    private Map<String, Long> longMap = new HashMap<String, Long>();
    private Map<String, String> stringMap = new HashMap<String, String>();

    private static Singleton instance = new Singleton();
    private Singleton() {
    }

    public static Long getLong(String key, Long defaultValue) {
        Map<String, Long> longMap = Singleton.getInstance().getLongMap();

        Long value = longMap.get(key);
        if (value == null) {
            value = defaultValue;
            longMap.put(key, value);
        }

        return value;
    }

    public static String getString(String key, String defaultaValue) {
        Map<String, String> stringMape = Singleton.getInstance().getStringMap();
        String value = stringMape.get(key);

        if (value == null) {
            value = defaultaValue;
            stringMape.put(key, value);
        }

        return value;
    }

    public Map<String, Long> getLongMap() {
        return longMap;
    }

    public void setLongMap(Map<String, Long> longMap) {
        this.longMap = longMap;
    }

    public Map<String, String> getStringMap() {
        return stringMap;
    }

    public void setStringMap(Map<String, String> stringMap) {
        this.stringMap = stringMap;
    }

    public static Singleton getInstance() {
        return instance;
    }

    public static void setInstance(Singleton instance) {
        Singleton.instance = instance;
    }
}
