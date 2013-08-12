package com.kelepi.dal.dataobject;

import javax.persistence.*;
import java.util.Date;

/**
 * User: liWeiLin
 * Date: 13-8-11 上午9:26
 */
@Entity
@Table(name="t_joke")
public class JokeDO extends BaseDO {
    private Long id;
    private String title;
    private String contentImageUrl;
    private Integer mediumCategory;
    private Integer jokeCategory;
    private Integer recommendType;
    private String tags;
    private Integer status;
    private Integer topSize;
    private Integer downSize;
    private Integer funnySize;
    private Integer notFunnySize;
    private Long userId;
    private String userNickName;
    private Integer isDelete;
    private Date gmtCreate;
    private Date gmtModify;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContentImageUrl() {
        return contentImageUrl;
    }

    public void setContentImageUrl(String contentImageUrl) {
        this.contentImageUrl = contentImageUrl;
    }

    public Integer getMediumCategory() {
        return mediumCategory;
    }

    public void setMediumCategory(Integer mediumCategory) {
        this.mediumCategory = mediumCategory;
    }

    public Integer getJokeCategory() {
        return jokeCategory;
    }

    public void setJokeCategory(Integer jokeCategory) {
        this.jokeCategory = jokeCategory;
    }

    public Integer getRecommendType() {
        return recommendType;
    }

    public void setRecommendType(Integer recommendType) {
        this.recommendType = recommendType;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Column(updatable=false, insertable = false)
    public Integer getTopSize() {
        return topSize;
    }

    public void setTopSize(Integer topSize) {
        this.topSize = topSize;
    }

    @Column(updatable=false, insertable = false)
    public Integer getDownSize() {
        return downSize;
    }

    public void setDownSize(Integer downSize) {
        this.downSize = downSize;
    }

    @Column(updatable=false, insertable = false)
    public Integer getFunnySize() {
        return funnySize;
    }

    public void setFunnySize(Integer funnySize) {
        this.funnySize = funnySize;
    }

    @Column(updatable=false, insertable = false)
    public Integer getNotFunnySize() {
        return notFunnySize;
    }

    public void setNotFunnySize(Integer notFunnySize) {
        this.notFunnySize = notFunnySize;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    @Column(updatable=false, insertable = false)
    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer delete) {
        isDelete = delete;
    }

    @Column(updatable=false)
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
}
