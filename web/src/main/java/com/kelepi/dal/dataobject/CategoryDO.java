package com.kelepi.dal.dataobject;

import com.kelepi.util.ListUtil;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: liWeiLin
 * Date: 13-8-8 下午11:49
 */
@Entity
@Table(name = "t_category")
public class CategoryDO extends BaseDO{
    private Long id;
    private String name;
    private Integer indexf;
    private String description;
    private Long parentId;
    private Integer isDelete;
    private Date gmtCreate;
    private Date gmtModify;

    private List<CategoryDO> subCategoryDOs = new ArrayList<CategoryDO>();
    private List<CategoryDO> parentCategoryDOs = new ArrayList<CategoryDO>();
    private List<Object> entityList = new ArrayList<Object>();

    private List<CategoryExtensionDO> categoryExtensionDOs = new ArrayList<CategoryExtensionDO>();

    @Transient
    public List<CategoryDO> getSubCategorys() {
        List<CategoryDO> subCategorys = new ArrayList<CategoryDO>();

        for (CategoryDO subCategoryDO : subCategoryDOs) {
            if (subCategoryDO.getIsDelete() == 0) {
                subCategorys.add(subCategoryDO);
            }
        }

        return ListUtil.orderByProperties(subCategorys, "asc", "indexf");
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIndexf() {
        return indexf;
    }

    public void setIndexf(Integer indexf) {
        this.indexf = indexf;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Column(updatable=false, insertable=false)
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

    @Transient
    public List<CategoryDO> getSubCategoryDOs() {
        return subCategoryDOs;
    }
    public void setSubCategoryDOs(List<CategoryDO> subCategoryDOs) {
        this.subCategoryDOs = subCategoryDOs;
    }

    @Transient
    public List<CategoryDO> getParentCategoryDOs() {
        return parentCategoryDOs;
    }
    public void setParentCategoryDOs(List<CategoryDO> parentCategoryDOs) {
        this.parentCategoryDOs = parentCategoryDOs;
    }

    @Transient
    public List<Object> getEntityList() {
        return entityList;
    }
    public void setEntityList(List<Object> entityList) {
        this.entityList = entityList;
    }

    @Transient
    public List<CategoryExtensionDO> getCategoryExtensionDOs() {
        return categoryExtensionDOs;
    }

    public void setCategoryExtensionDOs(List<CategoryExtensionDO> categoryExtensionDOs) {
        this.categoryExtensionDOs = categoryExtensionDOs;
    }
}
