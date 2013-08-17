package com.kelepi.biz.ao;

import com.kelepi.dal.dataobject.UserDO;
import com.kelepi.dal.dataobject.WeiboRelationshipDO;
import com.qq.connect.javabeans.AccessToken;

/**
 * User: liWeiLin
 * Date: 13-8-4 下午9:36
 */
public interface QqAO {

    UserDO generateUser(AccessToken accessToken);
}
