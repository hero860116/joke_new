package com.kelepi.web.front.module.screen;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.kelepi.biz.ao.JokeAO;
import com.kelepi.common.bean.Result;
import com.kelepi.dal.dataobject.CategoryDO;
import com.kelepi.dal.dataobject.JokeDO;
import com.kelepi.dal.enums.MainStatus;
import com.kelepi.dal.queryobject.CategoryQuery;
import com.kelepi.dal.queryobject.JokeQuery;
import com.kelepi.web.common.BaseScreen;

import javax.annotation.Resource;
import java.util.List;

public class JokeReview extends BaseScreen {
	
	@Resource
	private JokeAO jokeAO;
	
	public void execute(@Param("page")Integer page, TurbineRunData rundata, Context context)
			throws Exception {
        JokeQuery jokeQuery = new JokeQuery();
        jokeQuery.setCurrentPage(page);

        Result result = jokeAO.getReviewJoke(jokeQuery);

        if (result.getModule("jokeDO") == null) {
            rundata.setRedirectLocation("reviewNomore.vm");
        }
        context.put("jokeDO", result.getModule("jokeDO"));
        context.put("userDO", result.getModule("userDO"));
        context.put("jokeQuery", jokeQuery);
        context.put("nextPage", jokeQuery.getCurrentPage()+1);
        context.put("index", "review");
	}
}
