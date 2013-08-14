package com.kelepi.web.front.module.screen;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.google.gson.Gson;
import com.kelepi.common.bean.Result;

/**
 * User: liWeiLin
 * Date: 13-8-14 下午9:18
 */
public class Json {
    public void execute(@Param("data")Object data, TurbineRunData rundata, Context context)
            throws Exception {
           rundata.setLayoutEnabled(false);
           context.put("jsonData", new Gson().toJson(data));
    }
}
