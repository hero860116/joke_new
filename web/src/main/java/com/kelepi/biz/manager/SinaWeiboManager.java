package com.kelepi.biz.manager;

import com.kelepi.biz.snsmanager.sinaweibo.Oauth;
import com.kelepi.biz.snsmanager.sinaweibo.http.AccessToken;
import com.kelepi.biz.snsmanager.sinaweibo.model.WeiboException;
import com.kelepi.biz.snsmanager.sinaweibo.util.BareBonesBrowserLaunch;
import org.springframework.stereotype.Component;

/**
 * User: liWeiLin
 * Date: 13-8-4 下午9:24
 */
@Component("sinaWeiboManager")
public class SinaWeiboManager {

    public String openLoginUrl() {
        String url = "";

        Oauth oauth = new Oauth();
        try {
            url = oauth.authorize("code", "", "");
        } catch (WeiboException e) {
            e.printStackTrace();
        }

        return url;
    }

    public AccessToken getAccessToken(String code) {
        Oauth oauth = new Oauth();
        AccessToken accessToken = null;

        try {
            accessToken = oauth.getAccessTokenByCode(code);
        } catch (WeiboException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return accessToken;
    }

}
