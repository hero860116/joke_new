package com.kelepi.dal.enums;

/**
 * User: liWeiLin
 * Date: 13-8-6 下午11:27
 */
public enum ViewPermissionsType {

    ALL(1, "所有用户"), LOGIN(2, "登录用户");
    private int type;
    private String message;

    private ViewPermissionsType(int type, String message) {
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
