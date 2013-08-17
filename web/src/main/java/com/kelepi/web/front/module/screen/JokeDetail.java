package com.kelepi.web.front.module.screen;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.google.gson.Gson;
import com.kelepi.biz.ao.JokeAO;
import com.kelepi.common.bean.Result;
import com.kelepi.web.common.BaseScreen;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * User: liWeiLin
 * Date: 13-8-14 下午9:18
 */
public class JokeDetail extends BaseScreen{

    @Autowired
    private JokeAO jokeAO;

    public void execute(@Param("jokeId")long jokeId, TurbineRunData rundata, Context context)
            throws Exception {

        Result result = jokeAO.jokeDetail(jokeId);

        context.put("jokeDO", result.getModule("jokeDO"));
        context.put("userDO", result.getModule("userDO"));
        context.put("topUserDOs", result.getModule("topUserDOs"));
        context.put("positionJokeList", getPositionJokeList());
        context.put("currentLoginUser", getCurrentLoginUser());
    }
}
