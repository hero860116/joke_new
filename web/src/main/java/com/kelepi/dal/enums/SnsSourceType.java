package com.kelepi.dal.enums;

/**
 * User: liWeiLin
 * Date: 13-8-6 下午11:27
 */
public enum SnsSourceType {

    SINA_WEIBO(1, "新浪微博"), TENXUN_QQ(2, "腾讯QQ");
    private int type;
    private String message;

    private SnsSourceType(int type, String message) {
        this.type = type;
        this.message = message;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
