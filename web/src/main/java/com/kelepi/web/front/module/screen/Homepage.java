package com.kelepi.web.front.module.screen;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.kelepi.biz.ao.JokeAO;
import com.kelepi.common.bean.Result;
import com.kelepi.dal.dataobject.JokeDO;
import com.kelepi.dal.enums.MainStatus;
import com.kelepi.dal.enums.RecommendType;
import com.kelepi.dal.queryobject.JokeQuery;
import com.kelepi.web.common.BaseScreen;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * User: liWeiLin
 * Date: 13-8-4 下午11:30
 */
public class Homepage extends BaseScreen{

    @Autowired
    private JokeAO jokeAO;

    @Autowired
    private HttpServletRequest request;

    public void execute(@Param("page") int page, @Param("recommendType") Integer recommendType, Navigator nav, TurbineRunData rundata, Context context) {
        JokeQuery jokeQuery = new JokeQuery();
        jokeQuery.setCurrentPage(page);
        jokeQuery.setPageSize(2);
        jokeQuery.setRecommendType(recommendType);
        jokeQuery.setStatus(MainStatus.NORMAL.getType());

        Result result = jokeAO.findJokes(jokeQuery);
        context.put("jokeDOs", result.getModule("jokeDOs"));
        context.put("userDOs", result.getModule("userDOs"));
        context.put("jokeQuery", jokeQuery);

        if (RecommendType.HOT.getType().equals(recommendType)) {
            context.put("index", "hot");
        } else {
            context.put("index", "homepage");
        }

        context.put("positionJokeList", getPositionJokeList());
        context.put("currentLoginUser", getCurrentLoginUser());

    }
}
