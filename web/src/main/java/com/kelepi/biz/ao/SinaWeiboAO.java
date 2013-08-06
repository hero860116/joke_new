package com.kelepi.biz.ao;

import com.kelepi.biz.snsmanager.sinaweibo.model.User;
import com.kelepi.dal.dataobject.UserDO;

/**
 * User: liWeiLin
 * Date: 13-8-4 下午9:36
 */
public interface SinaWeiboAO {
    String getLoginUrl();

    UserDO generateUser(String code);
}
