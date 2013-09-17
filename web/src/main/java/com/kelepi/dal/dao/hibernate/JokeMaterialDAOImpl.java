package com.kelepi.dal.dao.hibernate;

import com.alibaba.citrus.util.StringUtil;
import com.kelepi.dal.dao.BaseDAO;
import com.kelepi.dal.dao.HibernateBaseDAO;
import com.kelepi.dal.dao.JokeMaterialDAO;
import com.kelepi.dal.dataobject.JokeMaterialDO;
import com.kelepi.dal.queryobject.JokeMaterialQuery;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * User: liWeiLin
 * Date: 13-9-17 下午9:52
 */
@Component("jokeMaterialDAO")
public class JokeMaterialDAOImpl extends HibernateBaseDAO implements JokeMaterialDAO {
    // name:jokeMaterial  Name:JokeMaterial
    public long save(JokeMaterialDO jokeMaterialDO) {
        jokeMaterialDO.setGmtCreate(new Date()) ;
        jokeMaterialDO.setGmtModify(new Date());
        return (Long)getHibernateTemplate().save(jokeMaterialDO);
    }

    public void update(JokeMaterialDO jokeMaterialDO) {
        jokeMaterialDO.setGmtModify(new Date());

        getHibernateTemplate().update(jokeMaterialDO);
    }

    public void delete(long id) {
        getSession().createQuery("update JokeMaterialDO set isDelete = 1 where id = :id").setLong("id", id).executeUpdate();
    }

    public JokeMaterialDO getJokeMaterial(long id) {

        return getHibernateTemplate().get(JokeMaterialDO.class, id);
    }

    public List<JokeMaterialDO> getJokeMaterialListByTemplate(JokeMaterialDO jokeMaterialDO) {

        return getHibernateTemplate().findByExample(jokeMaterialDO);
    }

    public List<JokeMaterialDO> findJokeMaterialsByQuery(JokeMaterialQuery jokeMaterialQuery) {

        Criteria criteria = getSession().createCriteria(JokeMaterialDO.class);
        criteria.add(Restrictions.eq("isDelete", 0));

        if (jokeMaterialQuery.getCategoryId() != null) {
            criteria.add(Restrictions.eq("categoryId", jokeMaterialQuery.getCategoryId()));
        }

        if (jokeMaterialQuery.getStatus() != null) {
            criteria.add(Restrictions.eq("status", jokeMaterialQuery.getStatus()));
        }

        if (!StringUtil.isEmpty(jokeMaterialQuery.getCategoryName())) {
            criteria.add(Restrictions.eq("categoryName", jokeMaterialQuery.getCategoryName()));
        }

        criteria.setProjection(Projections.rowCount());

        int totalItem = ((Long) criteria.uniqueResult()).intValue();
        jokeMaterialQuery.setTotalItem(totalItem);


        criteria = getSession().createCriteria(JokeMaterialDO.class);

        criteria.add(Restrictions.eq("isDelete", 0));

        if (jokeMaterialQuery.getCategoryId() != null) {
            criteria.add(Restrictions.eq("categoryId", jokeMaterialQuery.getCategoryId()));
        }

        if (jokeMaterialQuery.getStatus() != null) {
            criteria.add(Restrictions.eq("status", jokeMaterialQuery.getStatus()));
        }

        if (!StringUtil.isEmpty(jokeMaterialQuery.getCategoryName())) {
            criteria.add(Restrictions.eq("categoryName", jokeMaterialQuery.getCategoryName()));
        }

        if (jokeMaterialQuery.getFirstOrder() != null) {
            if ("desc".equals(jokeMaterialQuery.getFirstOrderSort())) {
                criteria.addOrder(Order.desc(jokeMaterialQuery.getFirstOrder()));
            } else {
                criteria.addOrder(Order.asc(jokeMaterialQuery.getFirstOrder()));
            }
        }

        if (jokeMaterialQuery.getSecondOrder() != null) {
            if ("desc".equals(jokeMaterialQuery.getSecondOrderSort())) {
                criteria.addOrder(Order.desc(jokeMaterialQuery.getSecondOrder()));
            } else {
                criteria.addOrder(Order.asc(jokeMaterialQuery.getSecondOrder()));
            }
        }

        return criteria.setFirstResult(jokeMaterialQuery.getStartRow()).setMaxResults(jokeMaterialQuery.getPageSize()).list();
    }

    public void updateStatus(int status, long id) {
        getSession().createQuery("update JokeMaterialDO set status = :status where id = :id").setInteger("status", status).setLong("id", id).executeUpdate();
    }
}
