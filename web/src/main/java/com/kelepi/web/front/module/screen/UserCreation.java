package com.kelepi.web.front.module.screen;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.google.gson.Gson;
import com.kelepi.biz.ao.JokeAO;
import com.kelepi.biz.ao.UserAO;
import com.kelepi.dal.constants.JokeConstants;
import com.kelepi.dal.dataobject.JokeDO;
import com.kelepi.dal.queryobject.JokeQuery;
import com.kelepi.web.common.BaseScreen;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * User: liWeiLin
 * Date: 13-8-14 下午9:18
 */
public class UserCreation extends BaseScreen{


    @Autowired
    private JokeAO jokeAO;

    public void execute(@Param("userId")long userId, @Param("page")int page, TurbineRunData rundata, Context context)
            throws Exception {
        JokeQuery jokeQuery = new JokeQuery();
        jokeQuery.setCurrentPage(page);
        jokeQuery.setPageSize(JokeConstants.FRONT_PAGE_SZIE);
        jokeQuery.setUserId(userId);
        jokeQuery.setFirstOrder("gmtCreate");
        jokeQuery.setFirstOrderSort("desc");
        List<JokeDO> jokeDOs = jokeAO.findJokesByQuery(jokeQuery);

        context.put("jokeDOs", jokeDOs);
        context.put("jokeQuery", jokeQuery);
        context.put("positionJokeList", getPositionJokeList());
        context.put("currentLoginUser", getCurrentLoginUser());
    }
}
