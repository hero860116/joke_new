package com.kelepi.dal.dao.hibernate;

import com.kelepi.dal.dao.HibernateBaseDAO;
import com.kelepi.dal.dao.JokeInteractionRecordDAO;
import com.kelepi.dal.dataobject.JokeInteractionRecordDO;
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
@Component("jokeInteractionRecordDAO")
public class JokeInteractionRecordDAOImpl extends HibernateBaseDAO implements JokeInteractionRecordDAO {
    public long save(JokeInteractionRecordDO jokeInteractionRecordDO) {
        jokeInteractionRecordDO.setGmtCreate(new Date()) ;
        jokeInteractionRecordDO.setGmtModify(new Date());
        return (Long)getHibernateTemplate().save(jokeInteractionRecordDO);
    }

    public void update(JokeInteractionRecordDO jokeInteractionRecordDO) {
        jokeInteractionRecordDO.setGmtModify(new Date());

        getHibernateTemplate().update(jokeInteractionRecordDO);
    }

    public void delete(long id) {
        getSession().createQuery("update JokeInteractionRecordDO set isDelete = 1 where id = :id").setLong("id", id).executeUpdate();
    }

    public JokeInteractionRecordDO getJokeInteractionRecord(long id) {

        return getHibernateTemplate().get(JokeInteractionRecordDO.class, id);
    }

    public List<JokeInteractionRecordDO> getJokeInteractionRecordListByTemplate(JokeInteractionRecordDO jokeInteractionRecordDO) {

        return getHibernateTemplate().findByExample(jokeInteractionRecordDO);
    }
}
