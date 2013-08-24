package com.kelepi.web.admin.module.screen;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.kelepi.biz.ao.CategoryAO;
import com.kelepi.biz.ao.PicMaterialAO;
import com.kelepi.dal.dataobject.CategoryDO;
import com.kelepi.dal.dataobject.PicMaterialDO;
import com.kelepi.web.common.BaseScreen;

import javax.annotation.Resource;

public class AddModifyPicMaterial extends BaseScreen {
	
	@Resource
	private PicMaterialAO picMaterialAO;
	
	
	public void execute(@Param("id") long id,  Navigator nav, TurbineRunData rundata, Context context)
			throws Exception {

        PicMaterialDO picMaterialDO = new PicMaterialDO();
		if (id > 0) {
            picMaterialDO = picMaterialAO.getPicMaterial(id);
		} 

		context.put("picMaterialDO", picMaterialDO);
		context.put("id", id);
	}
}
