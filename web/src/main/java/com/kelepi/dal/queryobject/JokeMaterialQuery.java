package com.kelepi.dal.queryobject;

/**
 * User: liWeiLin
 * Date: 13-8-24 下午3:54
 */
public class JokeMaterialQuery extends BaseQuery{

    private Long categoryId;

    private String categoryName;

    private Integer status;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
