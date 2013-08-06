package com.kelepi.web.front.module.action;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.turbine.dataresolver.FormGroup;
import com.kelepi.biz.ao.UserAO;
import com.kelepi.dal.dataobject.UserDO;
import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * User: liWeiLin
 * Date: 13-8-7 上午12:30
 */
public class UserAction {

    @Autowired
    private UserAO userAO;

    public void doUpdateUser(@FormGroup("register") UserDO userDO, Navigator nav, TurbineRunData rundata, Context context)  {
            System.out.println(userDO);
    }
}
