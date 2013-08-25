package com.kelepi.web.admin.module.screen;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.kelepi.biz.ao.JokeAO;
import com.kelepi.biz.ao.PicMaterialAO;
import com.kelepi.dal.dataobject.JokeDO;
import com.kelepi.dal.dataobject.PicMaterialDO;
import com.kelepi.dal.queryobject.JokeQuery;
import com.kelepi.dal.queryobject.PicMaterialQuery;
import com.kelepi.web.common.BaseScreen;

import javax.annotation.Resource;
import java.util.List;

public class PicMaterialList extends BaseScreen {
	
	@Resource
	private PicMaterialAO picMaterialAO;
	
	public void execute(@Param("seriesName") String seriesName, @Param("roleName")String roleName, @Param("status") int status, @Param("page")Integer page, TurbineRunData rundata, Context context)
			throws Exception {

        PicMaterialQuery picMaterialQuery = new PicMaterialQuery();
        picMaterialQuery.setSeriesName(seriesName);
        picMaterialQuery.setRoleName(roleName);
        picMaterialQuery.setCurrentPage(page);
        picMaterialQuery.setStatus(status);

        List<PicMaterialDO> picMaterialDOs = picMaterialAO.findPicMaterialsByQuery(picMaterialQuery);
        context.put("picMaterialDOs", picMaterialDOs);
        context.put("picMaterialQuery", picMaterialQuery);
	}
}
