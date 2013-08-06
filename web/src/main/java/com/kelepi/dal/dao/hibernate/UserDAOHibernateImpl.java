package com.kelepi.dal.dao.hibernate;

import com.kelepi.dal.dao.HibernateBaseDAO;
import com.kelepi.dal.dao.UserDAO;
import com.kelepi.dal.dataobject.UserDO;
import org.springframework.stereotype.Component;

/**
 * User: liWeiLin
 * Date: 13-8-6 下午11:12
 */
@Component("userDAO")
public class UserDAOHibernateImpl extends HibernateBaseDAO implements UserDAO {
    public void save(UserDO userDO) {
        getHibernateTemplate().save(userDO);
    }

    public void update(UserDO userDO) {
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
        UserDO userDO = (UserDO)getSession().createQuery("from UserDO u where u.sourceType = :sourceType and u.sourceId = :sourceId").uniqueResult();
        return userDO;
    }
}