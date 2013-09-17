package com.kelepi.web.admin.module.screen;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.kelepi.biz.ao.JokeMaterialAO;
import com.kelepi.dal.dataobject.JokeMaterialDO;
import com.kelepi.dal.dataobject.PicMaterialDO;
import com.kelepi.dal.queryobject.JokeMaterialQuery;
import com.kelepi.web.common.BaseScreen;

import javax.annotation.Resource;
import java.util.List;

public class JokeMaterialList extends BaseScreen {
	
	@Resource
	private JokeMaterialAO jokeMaterialAO;
	
	public void execute(@Param("categoryName") String categoryName, @Param("status") int status, @Param("page")Integer page, TurbineRunData rundata, Context context)
			throws Exception {

        JokeMaterialQuery jokeMaterialQuery = new JokeMaterialQuery();
        jokeMaterialQuery.setCategoryName(categoryName);
        jokeMaterialQuery.setCurrentPage(page);
        jokeMaterialQuery.setStatus(status);

        List<JokeMaterialDO> jokeMaterialDOs = jokeMaterialAO.findJokeMaterialsByQuery(jokeMaterialQuery);
        context.put("jokeMaterialDOs", jokeMaterialDOs);
        context.put("jokeMaterialQuery", jokeMaterialQuery);
	}
}
