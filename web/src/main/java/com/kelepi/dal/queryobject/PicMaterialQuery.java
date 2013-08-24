package com.kelepi.dal.queryobject;

/**
 * User: liWeiLin
 * Date: 13-8-24 下午3:54
 */
public class PicMaterialQuery extends BaseQuery{
    private String seriesName;
    private String roleName;
    private String actorName;
    private Integer status;

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
