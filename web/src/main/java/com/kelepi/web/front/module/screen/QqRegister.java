package com.kelepi.web.front.module.screen;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.kelepi.biz.ao.QqAO;
import com.kelepi.biz.ao.UserAO;
import com.kelepi.biz.snsmanager.qqweibo.oauthv2.OAuthV2;
import com.kelepi.dal.dao.UserDAO;
import com.kelepi.dal.dataobject.UserDO;
import com.kelepi.dal.enums.MainStatus;
import com.kelepi.dal.enums.PermissionsType;
import com.kelepi.dal.enums.SnsSourceType;
import com.kelepi.util.DateUtil;
import com.kelepi.web.common.BaseScreen;
import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.oauth.Oauth;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;

/**
 * User: liWeiLin
 * Date: 13-8-4 下午11:30
 */
public class QqRegister extends BaseScreen{

    @Autowired
   private QqAO qqAO;

    @Autowired
    private HttpServletRequest request;

    public void execute(Navigator nav, TurbineRunData rundata, Context context) {

        try {
            AccessToken accessTokenObj = new Oauth().getAccessTokenByRequest(request);

            UserDO userDO = qqAO.generateUser(accessTokenObj);

            setCurrentLoginUser(userDO);

            if (userDO.getEmail() != null) {
                nav.redirectToLocation(getTurbineURIBroker("jokeModule").render());
                return;
            } else {
                nav.redirectToLocation(getTurbineURIBroker("jokeModule").setTarget("completeUserInfo.vm").render());
                return;
            }
        } catch (QQConnectException e) {
            e.printStackTrace();
        }
    }
}
