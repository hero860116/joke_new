package com.kelepi.web.front.module.screen;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.kelepi.biz.ao.UserAO;
import com.kelepi.web.common.BaseScreen;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * User: liWeiLin
 * Date: 13-8-4 下午11:30
 */
public class CompleteUserInfo extends BaseScreen{

    @Autowired
    private UserAO userAO;

    @Autowired
    private HttpServletRequest request;

    public void execute(Navigator nav, TurbineRunData rundata, Context context) {
       context.put("currentLoginUser", getCurrentLoginUser());
    }
}
