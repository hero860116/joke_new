package com.kelepi.dal.dao.hibernate;

import com.kelepi.dal.dao.HibernateBaseDAO;
import com.kelepi.dal.dao.CategoryExtensionDAO;
import com.kelepi.dal.dataobject.CategoryExtensionDO;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: liWeiLin
 * Date: 13-8-11 上午9:40
 */
@Component("categoryExtensionDAO")
public class CategoryExtensionDAOImpl extends HibernateBaseDAO implements CategoryExtensionDAO {
    public long save(CategoryExtensionDO categoryExtensionDO) {
        return (Long)getHibernateTemplate().save(categoryExtensionDO);
    }

    public void update(CategoryExtensionDO categoryExtensionDO) {

        getHibernateTemplate().update(categoryExtensionDO);
    }

    public void delete(long id) {
        CategoryExtensionDO categoryExtensionDO = new CategoryExtensionDO();
        categoryExtensionDO.setId(id);

        getHibernateTemplate().delete(categoryExtensionDO);
    }

    public CategoryExtensionDO getCategoryExtension(long id) {

        return getHibernateTemplate().get(CategoryExtensionDO.class, id);
    }

    public List<CategoryExtensionDO> getCategoryExtensionListByTemplate(CategoryExtensionDO categoryExtensionDO) {

        return getHibernateTemplate().findByExample(categoryExtensionDO);
    }

    public List<CategoryExtensionDO> getCategoryExtensions(List<Long> cids) {

        List<CategoryExtensionDO> categoryExtensionDOs = getSession().createQuery("from CategoryExtensionDO where cid in (:cids)")
                .setParameterList("cids", cids).list();

        return categoryExtensionDOs;
    }

    public List<CategoryExtensionDO> getCategoryExtensions(long cid) {
        List<CategoryExtensionDO> categoryExtensionDOs = getSession().createQuery("from CategoryExtensionDO where cid = :cid")
                .setLong("cid", cid).list();

        return categoryExtensionDOs;
    }

    public Map<String, String> getCategoryExtensionMap(long cid) {
        List<CategoryExtensionDO> categoryExtensionDOs = getSession().createQuery("from CategoryExtensionDO where cid = :cid")
                .setLong("cid", cid).list();

        Map<String, String> nameValueMap = new HashMap<String, String>();

        for (CategoryExtensionDO categoryExtensionDO : categoryExtensionDOs) {
            nameValueMap.put(categoryExtensionDO.getName(), categoryExtensionDO.getValue());
        }


        return nameValueMap;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
