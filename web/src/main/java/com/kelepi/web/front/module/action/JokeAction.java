package com.kelepi.web.front.module.action;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.turbine.dataresolver.FormGroup;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.alibaba.citrus.util.StringUtil;
import com.kelepi.biz.ao.JokeAO;
import com.kelepi.dal.dataobject.JokeDO;
import com.kelepi.dal.enums.MainStatus;
import com.kelepi.dal.jsonobject.JokeSnsDO;
import com.kelepi.dal.queryobject.JokeQuery;
import com.kelepi.web.common.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

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

    public void doForbidJoke(@Param("id")long id, @Param("nextPage")int nextPage, Navigator nav, TurbineRunData rundata, Context context)  {

        jokeAO.forbidJoke(id);

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

    public void doBuilderJoke(@Param("pics")String[] pics, @Param("dialogues")String[] dialogues, @Param("title")String title, @Param("jokeCategory")int jokeCategory,@Param("tags")String tags,Navigator nav, TurbineRunData rundata, Context context) {
        String basePath = rundata.getRequest().getSession().getServletContext().getRealPath("");

        long jokeId = jokeAO.buildJoke(pics, dialogues, title, basePath, jokeCategory, tags);

        String nextJokeUrl = getTurbineURIBroker("jokeModule").setTarget("jokeDetail.vm").addQueryData("jokeId", jokeId).render();

        nav.redirectToLocation(nextJokeUrl);
    }

    public void doFindJoke(@Param("page")int page, @Param("firstOrder")String firstOrder, @Param("firstOrderSort")String firstOrderSort, @Param("pageSize")Integer pageSize, Navigator nav, TurbineRunData rundata, Context context) {
        String basePath = rundata.getRequest().getSession().getServletContext().getRealPath("");

        JokeQuery jokeQuery = new JokeQuery();
        if (pageSize == null) {
            pageSize = 10;
        }
        jokeQuery.setStatus(MainStatus.NORMAL.getType());
        jokeQuery.setCurrentPage(page);
        jokeQuery.setPageSize(pageSize);
        if (firstOrderSort == null) {
            firstOrderSort = "desc";
        }
        if (firstOrder == null) {
            firstOrder = "topSize";
        }

        jokeQuery.setFirstOrder(firstOrder);
        jokeQuery.setFirstOrderSort(firstOrderSort);

        jokeQuery.setSecondOrder("gmtCreate");
        jokeQuery.setSecondOrderSort("desc");

        List<JokeDO> jOkeDOList = jokeAO.findJokesByQuery(jokeQuery);

        List<JokeSnsDO> jokeSnsDOs = new ArrayList<JokeSnsDO>();
        for (JokeDO jokeDO : jOkeDOList) {
            JokeSnsDO jokeSnsDO = new JokeSnsDO();
            jokeSnsDO.setContentImageUrl(jokeDO.getContentImageUrl());
            jokeSnsDO.setDownSize(jokeDO.getDownSize());
            jokeSnsDO.setTitle(jokeDO.getTitle());
            jokeSnsDO.setTopSize(jokeDO.getTopSize());
            jokeSnsDOs.add(jokeSnsDO);
        }

        toSuccessJson(jokeSnsDOs);
    }
}
