package com.kelepi.web.admin.module.screen;

import javax.annotation.Resource;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.kelepi.biz.ao.CategoryAO;
import com.kelepi.dal.dataobject.CategoryDO;
import com.kelepi.dal.exception.ProcessNotPassRuntimeException;
import com.kelepi.web.common.BaseScreen;

public class AddModifyCategory extends BaseScreen {
	
	@Resource
	private CategoryAO categoryAO;
	
	
	public void execute(@Param("id") long id, @Param("parentId") long parentId,  Navigator nav, TurbineRunData rundata, Context context)
			throws Exception {

		CategoryDO categoryDO = new CategoryDO();
		if (id > 0) {
			categoryDO = categoryAO.getCategory(id);
		} 

		context.put("categoryDO", categoryDO);
		context.put("id", id);
		context.put("parentId", parentId);
	}
}
