package com.kelepi.dal.jsonobject;

import java.util.List;

/**
 * User: liWeiLin
 * Date: 13-9-16 下午10:06
 */
public class PicMaterialJson {
    private List<RoleJson> role;
    private List<String> img;
    private List<PageJson> page;

    public List<RoleJson> getRole() {
        return role;
    }

    public void setRole(List<RoleJson> role) {
        this.role = role;
    }

    public List<String> getImg() {
        return img;
    }

    public void setImg(List<String> img) {
        this.img = img;
    }

    public List<PageJson> getPage() {
        return page;
    }

    public void setPage(List<PageJson> page) {
        this.page = page;
    }
}
