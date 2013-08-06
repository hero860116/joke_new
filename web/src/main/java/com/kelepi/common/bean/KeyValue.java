package com.kelepi.common.bean;

/**
 * 业务辅助对象，暂且放到po中
 *
 * @author Administrator
 *
 */
public class KeyValue {

    private String key;

    private Object value;

    public KeyValue(String key, Object value) {
        super();
        this.key = key;
        this.value = value;
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
