package com.kelepi.dal.dataobject;

import javax.persistence.*;
import java.util.Date;

/**
 * User: liWeiLin
 * Date: 13-8-13 下午10:09
 */
@Entity
@Table(name="t_joke_interaction_record")
public class JokeInteractionRecordDO extends BaseDO {

    private Long id;
    private Long jokeId;
    private Long jokeUserId;
    private String jokeNickName;
    private String nickName;
    private Long userId;
    private Integer type;
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

    public Long getJokeId() {
        return jokeId;
    }

    public void setJokeId(Long jokeId) {
        this.jokeId = jokeId;
    }

    public Long getJokeUserId() {
        return jokeUserId;
    }

    public void setJokeUserId(Long jokeUserId) {
        this.jokeUserId = jokeUserId;
    }

    public String getJokeNickName() {
        return jokeNickName;
    }

    public void setJokeNickName(String jokeNickName) {
        this.jokeNickName = jokeNickName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

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

    @Column(updatable = false, insertable = false)
    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer delete) {
        isDelete = delete;
    }
}
