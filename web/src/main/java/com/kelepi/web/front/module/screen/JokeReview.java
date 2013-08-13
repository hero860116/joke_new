package com.kelepi.web.front.module.screen;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.kelepi.biz.ao.JokeAO;
import com.kelepi.common.bean.Result;
import com.kelepi.dal.queryobject.JokeQuery;
import com.kelepi.web.common.BaseScreen;

import javax.annotation.Resource;

public class JokeReview extends BaseScreen {
	
	@Resource
	private JokeAO jokeAO;
	
	public void execute(@Param("page")Integer page, TurbineRunData rundata, Context context)
			throws Exception {
        if (page == null) {
            page = 1;
        }

        Result result = jokeAO.getReviewJoke(page);

        if (result.getModule("jokeDO") == null) {
            if (page == 1) {
                rundata.setRedirectLocation("reviewNomore.vm");
            } else if (page > 1) {
                rundata.setRedirectLocation(getTurbineURIBroker("jokeModule").setTarget("jokeReview.vm").render());
            }

        }
        context.put("jokeDO", result.getModule("jokeDO"));
        context.put("userDO", result.getModule("userDO"));
        context.put("nextPage", page+1);
        context.put("index", "review");
	}
}
