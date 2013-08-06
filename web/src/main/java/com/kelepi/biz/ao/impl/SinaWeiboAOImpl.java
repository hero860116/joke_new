package com.kelepi.biz.ao.impl;

import com.kelepi.biz.ao.BaseAO;
import com.kelepi.biz.ao.SinaWeiboAO;
import com.kelepi.biz.manager.SinaWeiboManager;
import com.kelepi.biz.snsmanager.sinaweibo.Users;
import com.kelepi.biz.snsmanager.sinaweibo.http.AccessToken;
import com.kelepi.biz.snsmanager.sinaweibo.model.User;
import com.kelepi.biz.snsmanager.sinaweibo.model.WeiboException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * User: liWeiLin
 * Date: 13-8-4 下午9:37
 */
@Component("sinaWeiboAO")
public class SinaWeiboAOImpl extends BaseAO implements SinaWeiboAO{

    @Autowired
    private SinaWeiboManager sinaWeiboManager;

    public String getLoginUrl() {
        //To change body of implemented methods use File | Settings | File Templates.
        return sinaWeiboManager.openLoginUrl();
    }

    public User getUser(String code) {
        AccessToken accessToken = sinaWeiboManager.getAccessToken(code);

        Users um = new Users();
        um.client.setToken(accessToken.getAccessToken());

        User user = null;
        try {
            user = um.showUserById(accessToken.getUid());

        } catch (WeiboException e) {
            e.printStackTrace();
        }

        return user;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
