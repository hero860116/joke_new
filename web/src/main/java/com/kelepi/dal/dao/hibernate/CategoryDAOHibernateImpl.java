package com.kelepi.dal.dao.hibernate;

import java.util.Date;
import java.util.List;

import com.kelepi.dal.dao.CategoryDAO;
import com.kelepi.dal.dao.HibernateBaseDAO;
import com.kelepi.dal.dataobject.CategoryDO;
import com.kelepi.dal.queryobject.CategoryQuery;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("categoryDAO")
public class CategoryDAOHibernateImpl extends HibernateBaseDAO implements CategoryDAO {

	@Transactional
	public long save(CategoryDO categoryDO) {
		categoryDO.setGmtCreate(new Date());
		categoryDO.setGmtModify(new Date());

		return (Long) getHibernateTemplate().save(categoryDO);
	}

	@Transactional
	public void update(CategoryDO categoryDO) {
		categoryDO.setGmtModify(new Date());

		getHibernateTemplate().update(categoryDO);

	}


	@Transactional
	public void delete(long id) {
		getSession().createQuery("update CategoryDO set isDelete = 1, gmtModified = now() where id = :id").setLong("id", id).executeUpdate();
	}

	@Transactional
	public CategoryDO getCategory(long id) {

		CategoryDO categoryDO = (CategoryDO) getSession()
				.createQuery("from CategoryDO f where id = :id and isDelete = 0")
				.setLong("id", id).uniqueResult();

		return categoryDO;
	}
	

	@SuppressWarnings("unchecked")
	@Transactional
	public List<CategoryDO> getCategorysByTemplate(CategoryDO categoryDO) {
		return getHibernateTemplate().findByExample(categoryDO);
	}

	@SuppressWarnings("unchecked")
	public List<CategoryDO> findCategorysByQuery(CategoryQuery categoryQuery) {
		
		Criteria criteria = getSession().createCriteria(CategoryDO.class);
		criteria.add(Restrictions.eq("isDelete", 0));
		
		if (categoryQuery.getParentId() != null) {
			criteria.add(Restrictions.eq("parentId", categoryQuery.getParentId()));
		}

		criteria.setProjection(Projections.rowCount());

		int totalItem = ((Long) criteria.uniqueResult()).intValue();
		categoryQuery.setTotalItem(totalItem);
		

		criteria = getSession().createCriteria(CategoryDO.class);

		criteria.add(Restrictions.eq("isDelete", 0));
		
		if (categoryQuery.getParentId() != null) {
			criteria.add(Restrictions.eq("parentId", categoryQuery.getParentId()));
		}

		if (categoryQuery.getFirstOrder() != null) {
			if ("desc".equals(categoryQuery.getFirstOrderSort())) {
				criteria.addOrder(Order.desc(categoryQuery.getFirstOrder()));
			} else {
				criteria.addOrder(Order.asc(categoryQuery.getFirstOrder()));
			}
		}

		if (categoryQuery.getSecondOrder() != null) {
			if ("desc".equals(categoryQuery.getSecondOrderSort())) {
				criteria.addOrder(Order.desc(categoryQuery.getSecondOrder()));
			} else {
				criteria.addOrder(Order.asc(categoryQuery.getSecondOrder()));
			}
		}

		return criteria.setFirstResult(categoryQuery.getStartRow()).setMaxResults(categoryQuery.getPageSize()).list();
	}

    public List<CategoryDO> findCategorysByQueryNoPage(CategoryQuery categoryQuery) {


        Criteria criteria = getSession().createCriteria(CategoryDO.class);

        if (categoryQuery.getParentId() != null) {
            criteria.add(Restrictions.eq("parentId", categoryQuery.getParentId()));
        }

        if (categoryQuery.getFirstOrder() != null) {
            if ("desc".equals(categoryQuery.getFirstOrderSort())) {
                criteria.addOrder(Order.desc(categoryQuery.getFirstOrder()));
            } else {
                criteria.addOrder(Order.asc(categoryQuery.getFirstOrder()));
            }
        }

        if (categoryQuery.getSecondOrder() != null) {
            if ("desc".equals(categoryQuery.getSecondOrderSort())) {
                criteria.addOrder(Order.desc(categoryQuery.getSecondOrder()));
            } else {
                criteria.addOrder(Order.asc(categoryQuery.getSecondOrder()));
            }
        }

        return criteria.list();
    }

    public int updateIndexf(int indexf, long id) {
		return getSession().createQuery("update CategoryDO c set c.indexf = :indexf, c.gmtModify = now() where c.id = :id")
		            .setInteger("indexf", indexf).setLong("id", id).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<CategoryDO> getCategorysByParentId(long parentId) {
		return getSession().createQuery("from CategoryDO c where c.parentId = :parentId and isDelete = 0")
		           .setLong("parentId", parentId).list();
	}

	@SuppressWarnings("unchecked")
	public List<CategoryDO> getCategorysByIds(List<Long> ids) {
		return getSession().createQuery("from CategoryDO c where c.id in (:ids) and c.isDelete = 0")
		              .setParameterList("ids", ids).list();
	}


}
