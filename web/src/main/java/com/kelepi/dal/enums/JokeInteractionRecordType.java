package com.kelepi.dal.enums;

/**
 * User: liWeiLin
 * Date: 13-8-6 下午11:27
 */
public enum JokeInteractionRecordType {

    /* 审查 */
    REVIEW_FUNNY(1, "好笑"), REVIEW_NOTFUNNY(2, "不好笑"), REVIEW_PASS(3, "通过"),

    /* 表态 */
    POSITION_UP(11,"顶"), POSITION_DOWN(12, "踩"),

    /* 分享 */
    SHARE_QQ_FRIEND(21, "分享到QQ朋友"), SHARE_QQ_ZONE(22, "分享到QQ空间"), SHARE_SINA_WEIBO(22, "分享到新浪微博"),

    /* 评论 */
    COMMENT(31, "评论");


    private int type;
    private String message;

    private JokeInteractionRecordType(int type, String message) {
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
