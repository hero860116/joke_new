package com.kelepi.web.admin.module.screen;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.kelepi.biz.ao.JokeMaterialAO;
import com.kelepi.dal.dataobject.JokeMaterialDO;
import com.kelepi.web.common.BaseScreen;

import javax.annotation.Resource;

public class AddModifyJokeMaterial extends BaseScreen {
	
	@Resource
	private JokeMaterialAO jokeMaterialAO;
	
	
	public void execute(@Param("id") long id,  Navigator nav, TurbineRunData rundata, Context context)
			throws Exception {

        JokeMaterialDO jokeMaterialDO = new JokeMaterialDO();
		if (id > 0) {
            jokeMaterialDO = jokeMaterialAO.getJokeMaterial(id);
		} 

		context.put("jokeMaterialDO", jokeMaterialDO);
		context.put("id", id);
	}
}
