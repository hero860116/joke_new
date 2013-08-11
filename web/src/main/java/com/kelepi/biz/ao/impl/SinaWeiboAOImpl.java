package com.kelepi.biz.ao.impl;

import com.kelepi.biz.ao.BaseAO;
import com.kelepi.biz.ao.SinaWeiboAO;
import com.kelepi.biz.manager.SinaWeiboManager;
import com.kelepi.biz.snsmanager.sinaweibo.Users;
import com.kelepi.biz.snsmanager.sinaweibo.http.AccessToken;
import com.kelepi.biz.snsmanager.sinaweibo.model.User;
import com.kelepi.biz.snsmanager.sinaweibo.model.WeiboException;
import com.kelepi.dal.dao.UserDAO;
import com.kelepi.dal.dataobject.UserDO;
import com.kelepi.dal.enums.MainStatus;
import com.kelepi.dal.enums.PermissionsType;
import com.kelepi.dal.enums.SnsSourceType;
import com.kelepi.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

/**
 * User: liWeiLin
 * Date: 13-8-4 下午9:37
 */
@Component("sinaWeiboAO")
public class SinaWeiboAOImpl extends BaseAO implements SinaWeiboAO{

    Logger logger = LoggerFactory.getLogger(SinaWeiboAOImpl.class);

    @Autowired
    private SinaWeiboManager sinaWeiboManager;

    @Autowired
    private UserDAO userDAO;

    public String getLoginUrl() {
        //To change body of implemented methods use File | Settings | File Templates.
        return sinaWeiboManager.openLoginUrl();
    }

    public UserDO generateUser(String code) {
        if (getCurrentLoginUser() != null)  {
               return getCurrentLoginUser();
        }

        AccessToken accessToken = sinaWeiboManager.getAccessToken(code);

        Users um = new Users();
        um.client.setToken(accessToken.getAccessToken());

        UserDO userDO = userDAO.getUserBySource(SnsSourceType.SINA_WEIBO.getType(), accessToken.getUid());

        if (userDO == null) {
            try {
                User user = um.showUserById(accessToken.getUid());
                userDO = new UserDO();
                userDO.setAccessToken(accessToken.getAccessToken());
                int expireIn = Integer.parseInt(accessToken.getExpireIn());
                Date tokenExpireDate = DateUtil.addDuration(new Date(), Calendar.SECOND, expireIn);
                userDO.setTokenExpireDate(tokenExpireDate);
                userDO.setFaceImageUrl(user.getAvatarLarge());
                userDO.setNickName(user.getScreenName());
                userDO.setPermissions(PermissionsType.NORMAL.getType());
                userDO.setSourceId(accessToken.getUid());
                userDO.setSourceType(SnsSourceType.SINA_WEIBO.getType());
                userDO.setStatus(MainStatus.NORMAL.getType());

                userDAO.save(userDO);

            } catch (WeiboException e) {
                e.printStackTrace();
                logger.error("WeiboException", e);
            }
        } else if (!userDO.getAccessToken().equals(accessToken.getAccessToken())) {
            userDO.setAccessToken(accessToken.getAccessToken());
            int expireIn = Integer.parseInt(accessToken.getExpireIn());
            Date tokenExpireDate = DateUtil.addDuration(new Date(), Calendar.SECOND, expireIn);
            userDO.setTokenExpireDate(tokenExpireDate);
            userDAO.update(userDO);
        }
        return userDO;
    }
}
