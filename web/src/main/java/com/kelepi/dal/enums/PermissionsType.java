package com.kelepi.dal.enums;

/**
 * User: liWeiLin
 * Date: 13-8-6 下午11:27
 */
public enum PermissionsType {

    NORMAL(1, "普通用户"), ADMIN_MANAGE(2, "系统管理员");
    private int type;
    private String message;

    private PermissionsType(int type, String message) {
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
