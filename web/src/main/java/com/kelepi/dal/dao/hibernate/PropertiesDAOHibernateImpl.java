package com.kelepi.dal.dao.hibernate;

import com.kelepi.dal.dao.HibernateBaseDAO;
import com.kelepi.dal.dao.PropertiesDAO;
import com.kelepi.dal.dataobject.PropertiesDO;

import java.util.List;

/**
 * User: liWeiLin
 * Date: 13-8-10 上午9:34
 */
public class PropertiesDAOHibernateImpl extends HibernateBaseDAO implements PropertiesDAO{


    public long save(PropertiesDO propertiesDO) {
        return (Long) getHibernateTemplate().save(propertiesDO);
    }

    public void update(PropertiesDO propertiesDO) {
        getHibernateTemplate().update(propertiesDO);
    }

    public void delete(long id) {
        PropertiesDO propertiesDO = new PropertiesDO();
        propertiesDO.setId(id);

        getHibernateTemplate().delete(propertiesDO);
    }

    public PropertiesDO getProperties(long id) {
        return (PropertiesDO)getHibernateTemplate().get(PropertiesDO.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<PropertiesDO> getPropertiessByTemplate(PropertiesDO properties) {
        return getHibernateTemplate().findByExample(properties);
    }

    public PropertiesDO getPropertiesByName(String name) {
        return (PropertiesDO)getSession().createQuery("from PropertiesDO p where p.name = :name").setString("name", name).uniqueResult();
    }

    public void updatePropertiesValue(String name, String value) {
        getSession().createQuery("update PropertiesDO p set p.value = :value where p.name = :name").setString("name", name).setString("value", value).executeUpdate();
    }

}
