package com.kelepi.web.front.module.screen;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.kelepi.biz.ao.JokeAO;
import com.kelepi.common.bean.Result;
import com.kelepi.dal.enums.RecommendType;
import com.kelepi.dal.queryobject.JokeQuery;
import com.kelepi.web.common.BaseScreen;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * User: liWeiLin
 * Date: 13-8-4 下午11:30
 */
public class AddTopic extends BaseScreen{

    public void execute(Navigator nav, TurbineRunData rundata, Context context) {

          context.put("index", "addTopic");

    }
}
