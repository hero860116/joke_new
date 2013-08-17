package com.kelepi.biz.ao;

import com.kelepi.dal.dataobject.UserDO;

/**
 * User: liWeiLin
 * Date: 13-8-6 下午11:19
 */
public interface UserAO {
    void save(UserDO userDO);

    void updateInfo(String nikeName, String email, String faceImageUrl, long id, String signature);

    UserDO getUserDO(long id);
}
