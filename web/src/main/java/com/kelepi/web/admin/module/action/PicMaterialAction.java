package com.kelepi.web.admin.module.action;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.turbine.dataresolver.FormGroup;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.kelepi.biz.ao.JokeAO;
import com.kelepi.biz.ao.PicMaterialAO;
import com.kelepi.dal.dataobject.JokeDO;
import com.kelepi.dal.dataobject.PicMaterialDO;
import com.kelepi.web.common.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * User: liWeiLin
 * Date: 13-8-17 下午12:08
 */
public class PicMaterialAction extends BaseAction{

    @Autowired
    private PicMaterialAO picMaterialAO;


    public void doSave(@FormGroup("addModifyPicMaterial") PicMaterialDO picMaterialDO, @Param("referer")String referer, Navigator nav, TurbineRunData rundata, Context context) {

       picMaterialAO.save(picMaterialDO);

        nav.redirectToLocation(referer);
    }

    public void doUpdate(@FormGroup("addModifyPicMaterial") PicMaterialDO picMaterialDO, @Param("referer")String referer, Navigator nav, TurbineRunData rundata, Context context) {

        picMaterialAO.update(picMaterialDO);

        nav.redirectToLocation(referer);
    }

    public void doDelete(@Param("id")long id , @Param("referer")String referer, Navigator nav, TurbineRunData rundata, Context context) {
        picMaterialAO.delete(id);

        nav.redirectToLocation(referer);
    }

    public void doReviewPass(@Param("id")long id , @Param("referer")String referer, Navigator nav, TurbineRunData rundata, Context context) {
        picMaterialAO.reviewPass(id);

        nav.redirectToLocation(referer);
    }

}
