package com.kelepi.web.admin.module.action;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.kelepi.biz.ao.JokeAO;
import com.kelepi.web.common.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * User: liWeiLin
 * Date: 13-8-17 下午12:08
 */
public class JokeAction extends BaseAction{

    @Autowired
    private JokeAO jokeAO;

    public void doUpdateViewPermissions(@Param("viewPermissionsType") Integer viewPermissionsType, @Param("jokeId")long jokeId, Navigator nav, TurbineRunData rundata, Context context) {

        if (viewPermissionsType != null) {
            jokeAO.updateViewPermissions(jokeId, viewPermissionsType);
        }

        String rediUrl = getTurbineURIBroker("adminModule").setTarget("jokeList.vm").render();

        nav.redirectToLocation(rediUrl);
    }

    public void doUpdateRecommendType(@Param("recommendType") Integer recommendType, @Param("jokeId")long jokeId, Navigator nav, TurbineRunData rundata, Context context) {

        if (recommendType != null) {
            jokeAO.updateRecommendType(recommendType, jokeId);
        }

        String rediUrl = getTurbineURIBroker("adminModule").setTarget("jokeList.vm").render();

        nav.redirectToLocation(rediUrl);
    }
}
