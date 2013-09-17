package com.kelepi.web.front.module.screen;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.kelepi.biz.ao.CategoryAO;
import com.kelepi.biz.ao.PicMaterialAO;
import com.kelepi.common.bean.ParamInstance;
import com.kelepi.dal.dataobject.CategoryDO;
import com.kelepi.dal.dataobject.PicMaterialDO;
import com.kelepi.dal.enums.MainStatus;
import com.kelepi.dal.queryobject.PicMaterialQuery;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * User: liWeiLin
 * Date: 13-9-16 下午9:20
 */
public class ModalImg {
    @Autowired
    private CategoryAO categoryAO;

    @Autowired
    private PicMaterialAO picMaterialAO;

    public void execute(TurbineRunData rundata, Context context)
            throws Exception {

        rundata.setLayoutEnabled(false);
        List<CategoryDO> seCategoryList = new ArrayList<CategoryDO>();

        //获得剧集列表
        List<CategoryDO> categoryDOList =   ParamInstance.getCategory(36).getSubCategorys();
        for(CategoryDO categoryDO : categoryDOList) {
            List<CategoryDO> seraCategoryDOs =  categoryDO.getSubCategorys();

            seCategoryList.addAll(seraCategoryDOs);
        }

        List<CategoryDO> roleCategoryDOs =   ParamInstance.getCategory(seCategoryList.get(0).getId()).getSubCategorys();

        PicMaterialQuery picMaterialQuery = new PicMaterialQuery();
        picMaterialQuery.setSeriesId(seCategoryList.get(0).getId());
        picMaterialQuery.setStatus(MainStatus.NORMAL.getType());
        picMaterialQuery.setPageSize(2);
        List<PicMaterialDO> picMaterialDOs = picMaterialAO.findPicMaterialsByQuery(picMaterialQuery);

        context.put("categorys", seCategoryList);
        context.put("roleCategoryDOs", roleCategoryDOs);
        context.put("picMaterialDOs", picMaterialDOs);
        context.put("picMaterialQuery", picMaterialQuery);
    }
}
