package com.kelepi.web.admin.module.action;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.turbine.dataresolver.FormGroup;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.kelepi.biz.ao.JokeMaterialAO;
import com.kelepi.dal.dataobject.JokeMaterialDO;
import com.kelepi.web.common.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * User: liWeiLin
 * Date: 13-8-17 下午12:08
 */
public class JokeMaterialAction extends BaseAction{

    @Autowired
    private JokeMaterialAO jokeMaterialAO;


    public void doSave(@FormGroup("addModifyJokeMaterial") JokeMaterialDO jokeMaterialDO, @Param("referer")String referer, Navigator nav, TurbineRunData rundata, Context context) {

       jokeMaterialAO.save(jokeMaterialDO);

        nav.redirectToLocation(referer);
    }

    public void doUpdate(@FormGroup("addModifyJokeMaterial") JokeMaterialDO jokeMaterialDO, @Param("referer")String referer, Navigator nav, TurbineRunData rundata, Context context) {

        jokeMaterialAO.update(jokeMaterialDO);

        nav.redirectToLocation(referer);
    }

    public void doDelete(@Param("id")long id , @Param("referer")String referer, Navigator nav, TurbineRunData rundata, Context context) {
        jokeMaterialAO.delete(id);

        nav.redirectToLocation(referer);
    }

    public void doReviewPass(@Param("id")long id , @Param("referer")String referer, Navigator nav, TurbineRunData rundata, Context context) {
        jokeMaterialAO.reviewPass(id);

        nav.redirectToLocation(referer);
    }

}
