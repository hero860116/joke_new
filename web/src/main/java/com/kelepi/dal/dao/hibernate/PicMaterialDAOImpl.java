package com.kelepi.dal.dao.hibernate;

import com.kelepi.dal.dao.BaseDAO;
import com.kelepi.dal.dao.HibernateBaseDAO;
import com.kelepi.dal.dao.PicMaterialDAO;
import com.kelepi.dal.dataobject.PicMaterialDO;
import com.kelepi.dal.queryobject.PicMaterialQuery;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.Date;
import java.util.List;

/**
 * User: liWeiLin
 * Date: 13-8-24 下午4:13
 */
public class PicMaterialDAOImpl extends HibernateBaseDAO implements PicMaterialDAO{

    // name:picMaterial  Name:PicMaterial
    public long save(PicMaterialDO picMaterialDO) {
        picMaterialDO.setGmtCreate(new Date()) ;
        picMaterialDO.setGmtModify(new Date());
        return (Long)getHibernateTemplate().save(picMaterialDO);
    }

    public void update(PicMaterialDO picMaterialDO) {
        picMaterialDO.setGmtModify(new Date());

        getHibernateTemplate().update(picMaterialDO);
    }

    public void delete(long id) {
        getSession().createQuery("update PicMaterialDO set isDelete = 1 where id = :id").setLong("id", id).executeUpdate();
    }

    public PicMaterialDO getPicMaterial(long id) {

        return getHibernateTemplate().get(PicMaterialDO.class, id);
    }

    public List<PicMaterialDO> getPicMaterialListByTemplate(PicMaterialDO picMaterialDO) {

        return getHibernateTemplate().findByExample(picMaterialDO);
    }

    public List<PicMaterialDO> findPicMaterialsByQuery(PicMaterialQuery picMaterialQuery) {

        Criteria criteria = getSession().createCriteria(PicMaterialDO.class);
        criteria.add(Restrictions.eq("isDelete", 0));

        criteria.setProjection(Projections.rowCount());

        int totalItem = ((Long) criteria.uniqueResult()).intValue();
        picMaterialQuery.setTotalItem(totalItem);


        criteria = getSession().createCriteria(PicMaterialDO.class);

        criteria.add(Restrictions.eq("isDelete", 0));

        if (picMaterialQuery.getFirstOrder() != null) {
            if ("desc".equals(picMaterialQuery.getFirstOrderSort())) {
                criteria.addOrder(Order.desc(picMaterialQuery.getFirstOrder()));
            } else {
                criteria.addOrder(Order.asc(picMaterialQuery.getFirstOrder()));
            }
        }

        if (picMaterialQuery.getSecondOrder() != null) {
            if ("desc".equals(picMaterialQuery.getSecondOrderSort())) {
                criteria.addOrder(Order.desc(picMaterialQuery.getSecondOrder()));
            } else {
                criteria.addOrder(Order.asc(picMaterialQuery.getSecondOrder()));
            }
        }

        return criteria.setFirstResult(picMaterialQuery.getStartRow()).setMaxResults(picMaterialQuery.getPageSize()).list();
    }
}
