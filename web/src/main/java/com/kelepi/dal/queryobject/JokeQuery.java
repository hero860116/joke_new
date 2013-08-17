package com.kelepi.dal.queryobject;

import com.kelepi.dal.enums.RecommendType;

public class JokeQuery extends BaseQuery {
	
	private String title;
    private Integer status;
    private Integer recommendType;
    private Long userId;
    private Long preJokeId;
    private Long nextJokeId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRecommendType() {
        return recommendType;
    }

    public void setRecommendType(Integer recommendType) {
        this.recommendType = recommendType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPreJokeId() {
        return preJokeId;
    }

    public void setPreJokeId(Long preJokeId) {
        this.preJokeId = preJokeId;
    }

    public Long getNextJokeId() {
        return nextJokeId;
    }

    public void setNextJokeId(Long nextJokeId) {
        this.nextJokeId = nextJokeId;
    }
}
