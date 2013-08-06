package com.kelepi.web.front.module.action;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.kelepi.biz.ao.SinaWeiboAO;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * User: liWeiLin
 * Date: 13-8-4 下午9:39
 */
public class SinaWeiboAction {

    @Autowired
    private SinaWeiboAO sinaWeiboAO;

    /**
     * 转到sinaweibo登录页面
     * @param nav
     * @param rundata
     * @param context
     */
    public void doGotoLogin(Navigator nav, TurbineRunData rundata, Context context) {
        String loginUrl = sinaWeiboAO.getLoginUrl();
        rundata.setRedirectLocation(loginUrl);
    }

}
