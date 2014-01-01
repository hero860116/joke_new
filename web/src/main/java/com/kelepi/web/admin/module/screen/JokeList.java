package com.kelepi.web.admin.module.screen;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.kelepi.biz.ao.CategoryAO;
import com.kelepi.biz.ao.JokeAO;
import com.kelepi.dal.dataobject.CategoryDO;
import com.kelepi.dal.dataobject.JokeDO;
import com.kelepi.dal.queryobject.CategoryQuery;
import com.kelepi.dal.queryobject.JokeQuery;
import com.kelepi.web.common.BaseScreen;

import javax.annotation.Resource;
import java.util.List;

public class JokeList extends BaseScreen {
	
	@Resource
	private JokeAO jokeAO;
	
	public void execute(@Param("title") String title,@Param("page")Integer page, TurbineRunData rundata, Context context)
			throws Exception {

        JokeQuery jokeQuery = new JokeQuery();
        jokeQuery.setTitle(title);
        jokeQuery.setCurrentPage(page);
        jokeQuery.setFirstOrder("gmtCreate");
        jokeQuery.setFirstOrderSort("desc");
        List<JokeDO> jokeDOs = jokeAO.findJokesByQuery(jokeQuery);
        context.put("jokeDOs", jokeDOs);
        context.put("jokeQuery", jokeQuery);
	}
}
