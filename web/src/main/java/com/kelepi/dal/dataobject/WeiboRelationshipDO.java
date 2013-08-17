package com.kelepi.dal.dataobject;

/**
 * User: liWeiLin
 * Date: 13-8-17 上午11:04
 */
public class WeiboRelationshipDO {
    private String sinaWeiboId;
    private String qqWeiboId;

    private boolean isFans;

    public String getSinaWeiboId() {
        return sinaWeiboId;
    }

    public void setSinaWeiboId(String sinaWeiboId) {
        this.sinaWeiboId = sinaWeiboId;
    }

    public String getQqWeiboId() {
        return qqWeiboId;
    }

    public void setQqWeiboId(String qqWeiboId) {
        this.qqWeiboId = qqWeiboId;
    }

    public boolean isFans() {
        return isFans;
    }

    public void setFans(boolean fans) {
        isFans = fans;
    }
}

