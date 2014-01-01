package com.kelepi.web.front.module.screen;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.kelepi.biz.ao.CategoryAO;
import com.kelepi.biz.ao.JokeMaterialAO;
import com.kelepi.biz.ao.PicMaterialAO;
import com.kelepi.common.bean.ParamInstance;
import com.kelepi.dal.dataobject.CategoryDO;
import com.kelepi.dal.dataobject.JokeMaterialDO;
import com.kelepi.dal.dataobject.PicMaterialDO;
import com.kelepi.dal.enums.MainStatus;
import com.kelepi.dal.queryobject.JokeMaterialQuery;
import com.kelepi.dal.queryobject.PicMaterialQuery;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * User: liWeiLin
 * Date: 13-9-16 下午9:20
 */
public class ModalText {
    @Autowired
    private CategoryAO categoryAO;

    @Autowired
    private JokeMaterialAO jokeMaterialAO;

    public void execute(TurbineRunData rundata, Context context)
            throws Exception {

        rundata.setLayoutEnabled(false);

        //获得类别列表
        List<CategoryDO> categoryDOList =   ParamInstance.getCategory(41).getSubCategorys();

        JokeMaterialQuery jokeMaterialQuery = new JokeMaterialQuery();
        jokeMaterialQuery.setCategoryId(categoryDOList.get(0).getId());
        jokeMaterialQuery.setStatus(MainStatus.NORMAL.getType());
        jokeMaterialQuery.setPageSize(10);
        List<JokeMaterialDO> jokeMaterialDOs = jokeMaterialAO.findJokeMaterialsByQuery(jokeMaterialQuery);

        context.put("categorys", categoryDOList);
        context.put("jokeMaterialDOs", jokeMaterialDOs);
        context.put("jokeMaterialQuery", jokeMaterialQuery);
    }
}
