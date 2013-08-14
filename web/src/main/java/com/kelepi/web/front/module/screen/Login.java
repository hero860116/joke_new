package com.kelepi.web.front.module.screen;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.turbine.dataresolver.Param;

/**
 * User: liWeiLin
 * Date: 13-8-14 下午11:33
 */
public class Login {
    public void execute(@Param("index") String index, Navigator nav, TurbineRunData rundata, Context context) {

        context.put("index", index);

    }
}
