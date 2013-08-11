package com.kelepi.web.front.module.action;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.turbine.dataresolver.FormGroup;
import com.kelepi.biz.ao.JokeAO;
import com.kelepi.dal.dataobject.JokeDO;
import com.kelepi.dal.dataobject.UserDO;
import com.kelepi.web.common.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * User: liWeiLin
 * Date: 13-8-7 上午12:30
 */
public class JokeAction extends BaseAction{

    @Autowired
    private JokeAO jokeAO;

    public void doAddTopic(@FormGroup("addTopic") JokeDO jokeDO, Navigator nav, TurbineRunData rundata, Context context)  {
        jokeAO.addTopic(jokeDO);

        nav.redirectToLocation(getTurbineURIBroker("jokeModule").render());
    }
}
