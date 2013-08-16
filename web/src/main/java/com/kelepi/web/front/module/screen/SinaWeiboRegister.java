package com.kelepi.web.front.module.screen;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.kelepi.biz.ao.SinaWeiboAO;
import com.kelepi.dal.dataobject.UserDO;
import com.kelepi.web.common.BaseScreen;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * User: liWeiLin
 * Date: 13-8-4 下午11:30
 */
public class SinaWeiboRegister extends BaseScreen{

    @Autowired
    private SinaWeiboAO sinaWeiboAO;

    public void execute(@Param("code")String code,  @Param("error_code")String errorCode, Navigator nav, TurbineRunData rundata, Context context) {

        if (errorCode != null) {
            nav.redirectToLocation(getTurbineURIBroker("jokeModule").render());
            return;
        }
        UserDO userDO  = sinaWeiboAO.generateUser(code);
        setCurrentLoginUser(userDO);

        if (userDO.getEmail() != null) {
            nav.redirectToLocation(getTurbineURIBroker("jokeModule").render());
            return;
        } else {
            nav.redirectToLocation(getTurbineURIBroker("jokeModule").setTarget("completeUserInfo.vm").addQueryData("isSnsLogin", true).render());
            return;
        }
    }
}
