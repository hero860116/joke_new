package com.kelepi.web.front.module.control;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.kelepi.biz.ao.UserAO;
import com.kelepi.dal.dataobject.UserDO;
import com.kelepi.web.common.BaseControl;
import org.springframework.beans.factory.annotation.Autowired;


public class UserHead extends BaseControl {

    @Autowired
    private UserAO userAO;

	public void execute(@Param("userId")long userId, TurbineRunData rundata, Context context) {

        UserDO userDO = userAO.getUserDO(userId);
		context.put("userDO", userDO);
	}

}
