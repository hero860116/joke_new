package com.kelepi.dal.enums;

/**
 * User: liWeiLin
 * Date: 13-8-6 下午11:27
 */
public enum MainStatus {

    TO_REVIEW(0, "待审查"), NORMAL(1, "正常"), FORBID(2, "禁止");
    private int type;
    private String message;

    private MainStatus(int type, String message) {
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
