package com.kelepi.dal.dao;

import com.kelepi.dal.dataobject.PropertiesDO;

import java.util.List;


public interface PropertiesDAO {
	/**
	 * 保存
	 * @param propertiesDO
	 * @return TODO
	 */
	long save(PropertiesDO propertiesDO);
	
	/**
	 * 更新
	 * @param propertiesDO
	 */
	void update(PropertiesDO propertiesDO);

	
	/**
	 * 删除
	 * @param id
	 */
	void delete(long id);
	
	/**
	 * 根据主键获取
	 * @param id
	 * @return
	 */
	PropertiesDO getProperties(long id);
	
	/**
	 * 按模板查询
	 * @param propertiesDO
	 * @return
	 */
	List<PropertiesDO> getPropertiessByTemplate(PropertiesDO propertiesDO);
	
	/**
	 * 根据配置项名称获取配置值
	 * @param name
	 * @return
	 */
	PropertiesDO getPropertiesByName(String name);
	
	/**
	 * 更新配置项的值
	 * @param name
	 * @param value
	 */
	void updatePropertiesValue(String name, String value);
}
