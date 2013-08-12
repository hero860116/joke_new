package com.kelepi.biz.ao.impl;

import com.kelepi.biz.ao.BaseAO;
import com.kelepi.biz.ao.QqAO;
import com.kelepi.dal.dao.UserDAO;
import com.kelepi.dal.dataobject.UserDO;
import com.kelepi.dal.enums.MainStatus;
import com.kelepi.dal.enums.PermissionsType;
import com.kelepi.dal.enums.SnsSourceType;
import com.kelepi.util.DateUtil;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;

/**
 * User: liWeiLin
 * Date: 13-8-12 下午9:40
 */
@Component("qqAO")
public class QqAOImpl extends BaseAO implements QqAO {
    Logger logger = LoggerFactory.getLogger(QqAOImpl.class) ;

    @Autowired
    private UserDAO userDAO;

    @Transactional
    public UserDO generateUser(AccessToken accessTokenObj) {
        UserDO userDO = null;

        try {
            String accessToken = accessTokenObj.getAccessToken();

            Long tokenExpireIn = accessTokenObj.getExpireIn();

            OpenID openIDObj =  new OpenID(accessToken);
            String openID = openIDObj.getUserOpenID();

            userDO = userDAO.getUserBySource(SnsSourceType.TENXUN_QQ.getType(), openID);

            if (userDO == null) {
                UserInfo qzoneUserInfo = new UserInfo(accessToken, openID);
                UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();

                userDO = new UserDO();
                userDO.setTokenExpireDate(DateUtil.addDuration(new Date(), Calendar.SECOND, tokenExpireIn.intValue()));
                userDO.setNickName(userInfoBean.getNickname());
                userDO.setFaceImageUrl(userInfoBean.getAvatar().getAvatarURL100());
                userDO.setSourceType(SnsSourceType.TENXUN_QQ.getType());
                userDO.setAccessToken(accessToken);
                userDO.setSourceId(openID);
                userDO.setStatus(MainStatus.NORMAL.getType());
                userDO.setPermissions(PermissionsType.NORMAL.getType());

                userDAO.save(userDO);
            } else {
                userDO.setTokenExpireDate((DateUtil.addDuration(new Date(), Calendar.SECOND, tokenExpireIn.intValue())));
                userDAO.update(userDO);
            }
        }  catch (Exception e) {
            logger.warn("解析QQtomken登录消息出错", e);
        }


        return userDO;
    }
}
