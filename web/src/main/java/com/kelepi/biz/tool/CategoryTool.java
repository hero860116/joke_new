package com.kelepi.biz.tool;

import com.kelepi.common.bean.ParamInstance;
import com.kelepi.dal.dataobject.CategoryDO;

import java.util.List;

/**
 * User: liWeiLin
 * Date: 13-8-10 下午6:29
 */
public class CategoryTool {
    public List<CategoryDO> getSubCategoryList(long parentId) {
        List<CategoryDO> categoryDOList =   ParamInstance.getCategoryList(parentId);
        return categoryDOList;
    }
}
