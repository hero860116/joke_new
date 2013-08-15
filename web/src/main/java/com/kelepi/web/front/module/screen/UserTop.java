package com.kelepi.web.front.module.screen;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.kelepi.biz.ao.JokeAO;
import com.kelepi.dal.dataobject.JokeDO;
import com.kelepi.dal.enums.JokeInteractionRecordType;
import com.kelepi.dal.queryobject.JokeInteractionRecordQuery;
import com.kelepi.dal.queryobject.JokeQuery;
import com.kelepi.web.common.BaseScreen;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * User: liWeiLin
 * Date: 13-8-14 下午9:18
 */
public class UserTop extends BaseScreen{


    @Autowired
    private JokeAO jokeAO;

    public void execute(@Param("userId")long userId, @Param("page")int page, TurbineRunData rundata, Context context)
            throws Exception {
        JokeInteractionRecordQuery jokeInteractionRecordQuery = new JokeInteractionRecordQuery();
        jokeInteractionRecordQuery.setUserId(userId);
        jokeInteractionRecordQuery.setCurrentPage(page);
        jokeInteractionRecordQuery.setPageSize(5);
        jokeInteractionRecordQuery.setType(JokeInteractionRecordType.POSITION_UP.getType());

        List<JokeDO> jokeDOs = jokeAO.getTopJokeByQuery(jokeInteractionRecordQuery);

        context.put("jokeDOs", jokeDOs);
        context.put("jokeInteractionRecordQuery", jokeInteractionRecordQuery);
        context.put("positionJokeList", getPositionJokeList());
    }
}
