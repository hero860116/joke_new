package com.kelepi.dal.dao.hibernate;

import com.kelepi.dal.dao.HibernateBaseDAO;
import com.kelepi.dal.dao.UserDAO;
import com.kelepi.dal.dataobject.UserDO;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * User: liWeiLin
 * Date: 13-8-6 下午11:12
 */
@Component("userDAO")
public class UserDAOHibernateImpl extends HibernateBaseDAO implements UserDAO {
    public void save(UserDO userDO) {
        userDO.setGmtCreate(new Date());
        userDO.setGmtModify(new Date());
        getHibernateTemplate().save(userDO);
    }

    public void update(UserDO userDO) {
        userDO.setGmtModify(new Date());
        getHibernateTemplate().update(userDO);
    }

    public UserDO get(Long id) {

        return getHibernateTemplate().get(UserDO.class, id);
    }

    public void delete(Long id) {
        UserDO userDO = new UserDO();
        userDO.setId(id);
        getHibernateTemplate().delete(userDO);
    }

    public UserDO getUserBySource(Integer sourceType, String sourceId) {
        UserDO userDO = (UserDO)getSession().createQuery("from UserDO u where u.sourceType = :sourceType and u.sourceId = :sourceId")
                .setInteger("sourceType", sourceType).setString("sourceId", sourceId).uniqueResult();
        return userDO;
    }

    public void updateInfo(String nickName, String email, String faceImageUrl, String signature, Long id) {
        getSession().createQuery("update UserDO u set nickName = :nickName, email = :email, faceImageUrl = :faceImageUrl,signature=:signature  where id = :id")
                .setString("nickName", nickName).setString("email", email).setString("faceImageUrl", faceImageUrl).setString("signature", signature).setLong("id", id).executeUpdate();

    }

    public List<UserDO> getUserDOs(List<Long> userIds) {
        List<UserDO> userDOs = getSession().createQuery("from UserDO u where u.id in (:ids)")
                .setParameterList("ids", userIds).list();
        return userDOs;
    }
}