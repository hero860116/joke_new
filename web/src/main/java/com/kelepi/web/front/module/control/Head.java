package com.kelepi.web.front.module.control;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.kelepi.web.common.BaseControl;


public class Head extends BaseControl {
	public void execute(TurbineRunData rundata, Context context) {
		context.put("currentLoginUser", getCurrentLoginUser());
	}

}
