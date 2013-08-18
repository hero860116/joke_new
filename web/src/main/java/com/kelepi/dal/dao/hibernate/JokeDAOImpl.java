package com.kelepi.dal.dao.hibernate;

import com.kelepi.dal.dao.HibernateBaseDAO;
import com.kelepi.dal.dao.JokeDAO;
import com.kelepi.dal.dataobject.JokeDO;
import com.kelepi.dal.queryobject.JokeInteractionRecordQuery;
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

        if (jokeQuery.getPreJokeId() != null) {
             criteria.add(Restrictions.lt("id", jokeQuery.getPreJokeId()));
        }

        if (jokeQuery.getNextJokeId() != null) {
            criteria.add(Restrictions.gt("id", jokeQuery.getNextJokeId()));
        }

        if (jokeQuery.getTitle() != null) {
            criteria.add(Restrictions.like("title", "%" + jokeQuery.getTitle() + "%"));
        }

        if (jokeQuery.getRecommendType() != null) {
            criteria.add(Restrictions.eq("recommendType", jokeQuery.getRecommendType()));
        }

        if (jokeQuery.getStatus() != null) {
            criteria.add(Restrictions.eq("status", jokeQuery.getStatus()));
        }

        if (jokeQuery.getUserId() != null) {
            criteria.add(Restrictions.eq("userId", jokeQuery.getUserId()));
        }

        criteria.setProjection(Projections.rowCount());

        int totalItem = ((Long) criteria.uniqueResult()).intValue();
        jokeQuery.setTotalItem(totalItem);


        criteria = getSession().createCriteria(JokeDO.class);

        criteria.add(Restrictions.eq("isDelete", 0));

        if (jokeQuery.getPreJokeId() != null) {
            criteria.add(Restrictions.lt("id", jokeQuery.getPreJokeId()));
        }

        if (jokeQuery.getNextJokeId() != null) {
            criteria.add(Restrictions.gt("id", jokeQuery.getNextJokeId()));
        }

        if (jokeQuery.getTitle() != null) {
            criteria.add(Restrictions.like("title", "%" + jokeQuery.getTitle() + "%"));
        }

        if (jokeQuery.getRecommendType() != null) {
            criteria.add(Restrictions.eq("recommendType", jokeQuery.getRecommendType()));
        }

        if (jokeQuery.getStatus() != null) {
            criteria.add(Restrictions.eq("status", jokeQuery.getStatus()));
        }

        if (jokeQuery.getUserId() != null) {
            criteria.add(Restrictions.eq("userId", jokeQuery.getUserId()));
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

    public JokeDO findReviewJoke(int several, long userId) {

        JokeDO jokeDO = (JokeDO)getSession().createSQLQuery("select * from t_joke j left join t_joke_interaction_record r on j.id = r.jokeId and r.type in (1,2,3) and r.userId = :userId where j.isDelete = 0 and j.status = 0 and r.id is null order by j.gmtCreate desc")
                .addEntity(JokeDO.class).setLong("userId", userId).setFirstResult(several - 1).setMaxResults(1).uniqueResult();

        return  jokeDO;
}

    public void addFunnySize(int addSize, long id) {
        getSession().createQuery("update JokeDO set funnySize = funnySize + :addSize where id = :id")
                .setInteger("addSize", addSize).setLong("id", id).executeUpdate();
    }

    public void addNotFunnySize(int addSize, long id) {
        getSession().createQuery("update JokeDO set notFunnySize = notFunnySize + :addSize where id = :id")
                .setInteger("addSize", addSize).setLong("id", id).executeUpdate();
    }

    public void addTopSize(int addSize, long id) {
        getSession().createQuery("update JokeDO set topSize = topSize + :addSize where id = :id")
                .setInteger("addSize", addSize).setLong("id", id).executeUpdate();
    }

    public void addDownSize(int addSize, long id) {
        getSession().createQuery("update JokeDO set downSize = downSize + :addSize where id = :id")
                .setInteger("addSize", addSize).setLong("id", id).executeUpdate();
    }

    public void updateStatus(int status, long id) {
        getSession().createQuery("update JokeDO set status = :status where id = :id")
                .setInteger("status", status).setLong("id", id).executeUpdate();
    }

    /**
     * 改方法只能用于一对一的情况，如一个用户只能对一个joke顶一次，多余的都会不会记录
     * @param jokeInteractionRecordQuery@return
     * @return
     */
    public List<JokeDO> getTopJokeByUserId(JokeInteractionRecordQuery jokeInteractionRecordQuery) {
        List<JokeDO> jokeDOs = getSession().createSQLQuery("select j.* from t_joke j inner join t_joke_interaction_record r on j.id = r.jokeId where j.isDelete = 0 and r.type = :type and r.userId = :userId")
                .addEntity(JokeDO.class).setInteger("type", jokeInteractionRecordQuery.getType()).setLong("userId", jokeInteractionRecordQuery.getUserId()).setFirstResult(jokeInteractionRecordQuery.getStartRow()).setMaxResults(jokeInteractionRecordQuery.getPageSize()).list();
        return jokeDOs;
    }

    public void updateViewPermissions(long jokeId, int viewPermissionsType) {
        getSession().createQuery("update JokeDO j set j.viewPermissions = :viewPermissions where id = :id")
                .setInteger("viewPermissions", viewPermissionsType).setLong("id", jokeId).executeUpdate();
    }

    public void updateRecommendType(int recommendType, long jokeId) {
        getSession().createQuery("update JokeDO j set j.recommendType = :recommendType where id =:id")
                .setInteger("recommendType", recommendType).setLong("id", jokeId).executeUpdate();
    }
}
