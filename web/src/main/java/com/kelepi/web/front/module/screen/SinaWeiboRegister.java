package com.kelepi.web.front.module.screen;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.kelepi.biz.ao.SinaWeiboAO;
import com.kelepi.biz.snsmanager.sinaweibo.model.User;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * User: liWeiLin
 * Date: 13-8-4 下午11:30
 */
public class SinaWeiboRegister {

    @Autowired
    private SinaWeiboAO sinaWeiboAO;

    public void execute(@Param("code")String code,  Navigator nav, TurbineRunData rundata, Context context) {
        User user  = sinaWeiboAO.getUser(code);

        context.put("user", user);
    }
}
