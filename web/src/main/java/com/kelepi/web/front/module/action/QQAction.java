package com.kelepi.web.front.module.action;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.kelepi.biz.ao.SinaWeiboAO;
import com.kelepi.biz.ao.UserAO;
import com.kelepi.biz.manager.SinaWeiboManager;
import com.kelepi.dal.dao.UserDAO;
import com.kelepi.dal.dataobject.UserDO;
import com.kelepi.dal.enums.MainStatus;
import com.kelepi.dal.enums.PermissionsType;
import com.kelepi.dal.enums.SnsSourceType;
import com.kelepi.web.common.BaseAction;
import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.oauth.Oauth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * User: liWeiLin
 * Date: 13-8-4 下午9:39
 */
public class QQAction extends BaseAction{

    Logger logger = LoggerFactory.getLogger(QQAction.class);

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserAO userAO;

    /**
     * 转到sinaweibo登录页面
     * @param nav
     * @param rundata
     * @param context
     */
    public void doGotoLogin(Navigator nav, TurbineRunData rundata, Context context) {
        try {
            String url = new Oauth().getAuthorizeURL(request);
            rundata.setRedirectLocation(url);
        } catch (QQConnectException e) {
            e.printStackTrace();
            logger.warn("获取qq登录地址出错!", e);
        }
    }

    public void doLoginRedirect(@Param("code")String code, @Param("state")String state, Navigator nav, TurbineRunData rundata, Context context) {
        try {
            AccessToken accessTokenObj = (new Oauth()).getAccessTokenByRequest(request);
            String accessToken = accessTokenObj.getAccessToken();
            long tokenExpireIn = accessTokenObj.getExpireIn();

            OpenID openIDObj =  new OpenID(accessToken);
            String openID = openIDObj.getUserOpenID();

            UserInfo qzoneUserInfo = new UserInfo(accessToken, openID);
            UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();

            UserDO userDO = new UserDO();
            userDO.setNickName(userInfoBean.getNickname());
            userDO.setFaceImageUrl(userInfoBean.getAvatar().getAvatarURL100());
            userDO.setSourceType(SnsSourceType.TENXUN_QQ.getType());
            userDO.setAccessToken(accessToken);
            userDO.setSourceId(openID);
            userDO.setStatus(MainStatus.NORMAL.getType());
            userDO.setPermissions(PermissionsType.NORMAL.getType());

            userAO.save(userDO);

            setUserDO(userDO);
        } catch (QQConnectException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

}
