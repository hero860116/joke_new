package com.kelepi.dal.queryobject;

import com.kelepi.dal.enums.RecommendType;

public class JokeQuery extends BaseQuery {
	
	private String title;
    private Integer status;
    private Integer recommendType;

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
}
