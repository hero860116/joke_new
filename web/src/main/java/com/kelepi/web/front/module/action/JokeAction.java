package com.kelepi.web.front.module.action;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.turbine.dataresolver.FormGroup;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.kelepi.biz.ao.JokeAO;
import com.kelepi.dal.dataobject.JokeDO;
import com.kelepi.dal.queryobject.JokeQuery;
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

    public void doFunnyJoke(@Param("id")long id, @Param("nextPage")int nextPage, Navigator nav, TurbineRunData rundata, Context context)  {

        jokeAO.funnyJoke(id);

        nav.redirectToLocation(getTurbineURIBroker("jokeModule").setTarget("jokeReview.vm").addQueryData("page", nextPage).render());
    }

    public void doNotFunnyJoke(@Param("id")long id, @Param("nextPage")int nextPage, Navigator nav, TurbineRunData rundata, Context context)  {

        jokeAO.notFunnyJoke(id);

        nav.redirectToLocation(getTurbineURIBroker("jokeModule").setTarget("jokeReview.vm").addQueryData("page", nextPage).render());
    }

    public void doReviewPass(@Param("id")long id, @Param("nextPage")int nextPage, Navigator nav, TurbineRunData rundata, Context context)  {

        jokeAO.reviewPass(id);

        nav.redirectToLocation(getTurbineURIBroker("jokeModule").setTarget("jokeReview.vm").addQueryData("page", nextPage).render());
    }

    public void doTopJoke(@Param("id")long id, @Param("page")int page, Navigator nav, TurbineRunData rundata, Context context)  {

        jokeAO.topJoke(id);

        //返回json,攻前台调用
        toSuccessJson(null);
    }

    public void doDownJoke(@Param("id")long id, @Param("page")int page, Navigator nav, TurbineRunData rundata, Context context)  {

        jokeAO.downJoke(id);

        //返回json,攻前台调用
        toSuccessJson(null);
    }

    public void doGetNextJoke(@Param("jokeId")long jokeId, @Param("recommendType")Integer recommendType, Navigator nav, TurbineRunData rundata, Context context)  {

        JokeQuery jokeQuery = new JokeQuery();
        jokeQuery.setPreJokeId(jokeId);
        jokeQuery.setRecommendType(recommendType);

        JokeDO jokeDO = jokeAO.getNextJoke(jokeQuery);

        if (jokeDO == null) {
            jokeQuery.setPreJokeId(null);
            jokeDO = jokeAO.getNextJoke(jokeQuery);
        }

        String nextJokeUrl = getTurbineURIBroker("jokeModule").setTarget("jokeDetail.vm").addQueryData("jokeId", jokeDO.getId()).render();

        nav.redirectToLocation(nextJokeUrl);
    }

    public void doGetPreJoke(@Param("jokeId")long jokeId,  @Param("recommendType")Integer recommendType, Navigator nav, TurbineRunData rundata, Context context)  {

        JokeQuery jokeQuery = new JokeQuery();
        jokeQuery.setNextJokeId(jokeId);
        jokeQuery.setRecommendType(recommendType);

        JokeDO jokeDO = jokeAO.getPreJoke(jokeQuery);

        if (jokeDO == null) {
            jokeQuery.setNextJokeId(null);
            jokeDO = jokeAO.getPreJoke(jokeQuery);
        }

        String nextJokeUrl = getTurbineURIBroker("jokeModule").setTarget("jokeDetail.vm").addQueryData("jokeId", jokeDO.getId()).render();

        nav.redirectToLocation(nextJokeUrl);
    }
}
