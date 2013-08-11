package com.kelepi.web.front.module.screen;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.kelepi.biz.ao.UserAO;
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
    private UserAO userAO;

    @Autowired
    private HttpServletRequest request;

    public void execute(Navigator nav, TurbineRunData rundata, Context context) {

        try {
            AccessToken accessTokenObj = new Oauth().getAccessTokenByRequest(request) ;
            String accessToken = accessTokenObj.getAccessToken();

            System.out.println("**************************************");
            System.out.println(accessToken);
            System.out.println(accessTokenObj);
            System.out.println("**************************************");
            Long tokenExpireIn = accessTokenObj.getExpireIn();

            OpenID openIDObj =  new OpenID(accessToken);
            String openID = openIDObj.getUserOpenID();

            UserInfo qzoneUserInfo = new UserInfo(accessToken, openID);
            UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();

            UserDO userDO = new UserDO();
            userDO.setTokenExpireDate(DateUtil.addDuration(new Date(), Calendar.SECOND, tokenExpireIn.intValue()));
            userDO.setNickName(userInfoBean.getNickname());
            userDO.setFaceImageUrl(userInfoBean.getAvatar().getAvatarURL100());
            userDO.setSourceType(SnsSourceType.TENXUN_QQ.getType());
            userDO.setAccessToken(accessToken);
            userDO.setSourceId(openID);
            userDO.setStatus(MainStatus.NORMAL.getType());
            userDO.setPermissions(PermissionsType.NORMAL.getType());

            userAO.save(userDO);

            setCurrentLoginUser(userDO);
        } catch (QQConnectException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
