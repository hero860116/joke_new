package com.kelepi.dal.dataobject;

import javax.persistence.*;
import java.util.Date;

/**
 * User: liWeiLin
 * Date: 13-8-24 下午3:52
 */
@Entity
@Table(name="t_joke_material")
public class JokeMaterialDO extends BaseDO{
    private Long id;
    private String content;
    private Long categoryId;
    private String categoryName;
    private Integer status;
    private Date gmtCreate;
    private Date gmtModify;
    private Integer isDelete;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

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

    @Column(updatable = false)
    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }

    @Column(insertable =false, updatable = false)
    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        isDelete = isDelete;
    }
}
