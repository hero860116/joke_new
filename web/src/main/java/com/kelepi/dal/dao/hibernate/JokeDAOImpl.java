package com.kelepi.dal.dao.hibernate;

import com.kelepi.dal.dao.HibernateBaseDAO;
import com.kelepi.dal.dao.JokeDAO;
import com.kelepi.dal.dataobject.CategoryDO;
import com.kelepi.dal.dataobject.JokeDO;
import com.kelepi.dal.queryobject.JokeQuery;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * User: liWeiLin
 * Date: 13-8-11 上午9:40
 */
@Component("jokeDAO")
public class JokeDAOImpl extends HibernateBaseDAO implements JokeDAO {
    public long save(JokeDO jokeDO) {
        jokeDO.setGmtCreate(new Date()) ;
        jokeDO.setGmtModify(new Date());
        return (Long)getHibernateTemplate().save(jokeDO);
    }

    public void update(JokeDO jokeDO) {
        jokeDO.setGmtModify(new Date());

        getHibernateTemplate().update(jokeDO);
    }

    public void delete(long id) {
        getSession().createQuery("update JokeDO set isDelete = 1 where id = :id").setLong("id", id).executeUpdate();
    }

    public JokeDO getJoke(long id) {

        return getHibernateTemplate().get(JokeDO.class, id);
    }

    public List<JokeDO> getJokeListByTemplate(JokeDO jokeDO) {

        return getHibernateTemplate().findByExample(jokeDO);
    }

    public List<JokeDO> findJokeListByQuery(JokeQuery jokeQuery) {

        Criteria criteria = getSession().createCriteria(JokeDO.class);
        criteria.add(Restrictions.eq("isDelete", 0));

        if (jokeQuery.getTitle() != null) {
            criteria.add(Restrictions.like("title", "%" + jokeQuery.getTitle() + "%"));
        }

        if (jokeQuery.getRecommendType() != null) {
            criteria.add(Restrictions.eq("recommendType", jokeQuery.getRecommendType()));
        }

        if (jokeQuery.getStatus() != null) {
            criteria.add(Restrictions.eq("status", jokeQuery.getStatus()));
        }

        criteria.setProjection(Projections.rowCount());

        int totalItem = ((Long) criteria.uniqueResult()).intValue();
        jokeQuery.setTotalItem(totalItem);


        criteria = getSession().createCriteria(JokeDO.class);

        criteria.add(Restrictions.eq("isDelete", 0));

        if (jokeQuery.getTitle() != null) {
            criteria.add(Restrictions.like("title", "%" + jokeQuery.getTitle() + "%"));
        }

        if (jokeQuery.getRecommendType() != null) {
            criteria.add(Restrictions.eq("recommendType", jokeQuery.getRecommendType()));
        }

        if (jokeQuery.getStatus() != null) {
            criteria.add(Restrictions.eq("status", jokeQuery.getStatus()));
        }

        if (jokeQuery.getFirstOrder() != null) {
            if ("desc".equals(jokeQuery.getFirstOrderSort())) {
                criteria.addOrder(Order.desc(jokeQuery.getFirstOrder()));
            } else {
                criteria.addOrder(Order.asc(jokeQuery.getFirstOrder()));
            }
        }

        if (jokeQuery.getSecondOrder() != null) {
            if ("desc".equals(jokeQuery.getSecondOrderSort())) {
                criteria.addOrder(Order.desc(jokeQuery.getSecondOrder()));
            } else {
                criteria.addOrder(Order.asc(jokeQuery.getSecondOrder()));
            }
        }

        return criteria.setFirstResult(jokeQuery.getStartRow()).setMaxResults(jokeQuery.getPageSize()).list();
    }
}
