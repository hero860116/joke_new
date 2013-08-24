package com.kelepi.web.admin.module.action;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.kelepi.biz.ao.CategoryExtensionAO;
import com.kelepi.dal.dataobject.CategoryExtensionDO;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * User: liWeiLin
 * Date: 13-8-24 下午1:38
 */
public class CategoryExtensionAction {


    @Autowired
    private CategoryExtensionAO categoryExtensionAO;

    public void doSave(@Param("cid")Long cid, @Param("name") String name, @Param("value")String value, @Param("referer")String referer, Navigator nav, TurbineRunData rundata, Context context) {
        CategoryExtensionDO categoryExtensionDO = new CategoryExtensionDO();
        categoryExtensionDO.setCid(cid);
        categoryExtensionDO.setName(name);
        categoryExtensionDO.setValue(value);

        categoryExtensionAO.save(categoryExtensionDO);

        rundata.setRedirectLocation(referer);
    }

    public void doDelete(@Param("id")Long id, @Param("referer")String referer, Navigator nav, TurbineRunData rundata, Context context) {
        categoryExtensionAO.delete(id);

        rundata.setRedirectLocation(referer);
    }


}
