package com.kelepi.biz.ao;

import com.kelepi.biz.snsmanager.sinaweibo.model.User;

/**
 * User: liWeiLin
 * Date: 13-8-4 下午9:36
 */
public interface SinaWeiboAO {
    String getLoginUrl();

    User getUser(String code);
}
