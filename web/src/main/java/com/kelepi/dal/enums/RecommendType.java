package com.kelepi.dal.enums;

/**
 * User: liWeiLin
 * Date: 13-8-6 下午11:27
 */
public enum RecommendType {

    NORMAL(1, "普通"), HOT(2, "热门");
    private Integer type;
    private String message;

    private RecommendType(int type, String message) {
        this.type = type;
        this.message = message;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
