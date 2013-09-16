package com.kelepi.dal.jsonobject;

/**
 * User: liWeiLin
 * Date: 13-9-16 下午9:31
 */
public class RoleJson {
    private Long id;
    private String rolename;
    private String href;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
