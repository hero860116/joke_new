package com.kelepi.web.front.module.screen;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.kelepi.common.bean.Result;
import com.kelepi.web.common.BaseScreen;

/**
 * User: liWeiLin
 * Date: 13-8-25 下午7:21
 */
public class PicMaterialUploadBySeries extends BaseScreen{

    public void execute(@Param("seriesId")long seriesId, TurbineRunData rundata, Context context)
            throws Exception {

        context.put("seriesId", seriesId);
        context.put("index", "material");
    }
}
