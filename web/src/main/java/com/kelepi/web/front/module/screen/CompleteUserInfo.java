package com.kelepi.web.front.module.screen;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.kelepi.biz.ao.UserAO;
import com.kelepi.dal.dataobject.UserDO;
import com.kelepi.dal.enums.MainStatus;
import com.kelepi.dal.enums.PermissionsType;
import com.kelepi.dal.enums.SnsSourceType;
import com.kelepi.web.common.BaseScreen;
import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.oauth.Oauth;
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
       context.put("userDO", getUserDO());
    }
}
