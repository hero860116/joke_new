package com.kelepi.dal.dao;

import javax.persistence.Transient;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


public class HibernateBaseDAO extends HibernateDaoSupport {

	/**
	 * 提供ao层获取外部session，控制缓存
	 * @return
	 */
	@Transient
	public Session getSessionForOut() {
		return getSession();
	}
}
