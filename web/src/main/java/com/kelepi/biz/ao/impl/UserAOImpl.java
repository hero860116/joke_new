package com.kelepi.biz.ao.impl;

import com.kelepi.biz.ao.BaseAO;
import com.kelepi.biz.ao.UserAO;
import com.kelepi.dal.dao.UserDAO;
import com.kelepi.dal.dataobject.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * User: liWeiLin
 * Date: 13-8-6 下午11:20
 */
@Component("userAO")
public class UserAOImpl extends BaseAO implements UserAO {

    @Autowired
    private UserDAO userDAO;

    public void save(UserDO userDO) {
           userDAO.save(userDO);
    }

    public void updateInfo(String nikeName, String email, String faceImageUrl, long id) {
        //To change body of implemented methods use File | Settings | File Templates.
        userDAO.updateInfo(nikeName, email, faceImageUrl,id);
    }

    public UserDO getUserDO(long id) {
        return userDAO.get(id);
    }
}
