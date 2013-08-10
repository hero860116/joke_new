package com.kelepi.web.admin.module.screen;

import java.util.List;

import javax.annotation.Resource;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.kelepi.biz.ao.CategoryAO;
import com.kelepi.dal.dataobject.CategoryDO;
import com.kelepi.dal.queryobject.CategoryQuery;
import com.kelepi.web.common.BaseScreen;

public class CategoryList extends BaseScreen {
	
	@Resource
	private CategoryAO categoryAO;
	
	public void execute(@Param("parentId") long parentId, TurbineRunData rundata, Context context)
			throws Exception {
		CategoryQuery categoryQuery = new CategoryQuery();

		categoryQuery.setParentId(parentId);
		categoryQuery.setPageSize(20);
		categoryQuery.setFirstOrder("indexf");
		categoryQuery.setFirstOrderSort("asc");
		
		List<CategoryDO>  categoryDOs = categoryAO.findCategorysByQuery(categoryQuery);
		
		context.put("categoryDOs", categoryDOs);
		context.put("categoryQuery", categoryQuery);
	}
}
