package com.kelepi.dal.queryobject;

/**
 * User: liWeiLin
 * Date: 13-8-15 下午11:38
 */
public class JokeInteractionRecordQuery extends BaseQuery {
    private Long userId;

    private Integer type;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}

