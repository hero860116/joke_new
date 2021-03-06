package com.kelepi.web.front.module.action;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.turbine.dataresolver.FormGroup;
import com.kelepi.biz.ao.UserAO;
import com.kelepi.dal.dataobject.UserDO;
import com.kelepi.web.common.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * User: liWeiLin
 * Date: 13-8-7 上午12:30
 */
public class UserAction extends BaseAction{

    @Autowired
    private UserAO userAO;

    public void doUpdateUser(@FormGroup("register") UserDO userDO, Navigator nav, TurbineRunData rundata, Context context)  {
        userAO.updateInfo(userDO.getNickName(), userDO.getEmail(), userDO.getFaceImageUrl(), userDO.getId(), userDO.getSignature());

        UserDO userDO1 = getCurrentLoginUser();
        userDO1.setFaceImageUrl(userDO.getFaceImageUrl());
        userDO1.setEmail(userDO.getEmail());
        userDO1.setNickName(userDO.getNickName());
        userDO1.setSignature(userDO.getSignature());
        setCurrentLoginUser(userDO1);
        nav.redirectToLocation(getTurbineURIBroker("jokeModule").render());
    }

    public void doLogout(Navigator nav, TurbineRunData rundata, Context context) {
        removeCurrentLoginUser();
        nav.redirectToLocation(getTurbineURIBroker("jokeModule").render());
    }
}
