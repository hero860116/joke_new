package com.kelepi.biz.ao;

import com.kelepi.common.bean.Result;
import com.kelepi.dal.dataobject.CategoryExtensionDO;

import java.util.List;


public interface CategoryExtensionAO {
	
	/**
	 * 保存
	 * @param categoryExtension
	 */
	long save(CategoryExtensionDO categoryExtension);
	
	/**
	 * 更新
	 * @param categoryExtension
	 */
	void update(CategoryExtensionDO categoryExtension);
	
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
	CategoryExtensionDO getCategoryExtension(long id);
	
	/**
	 * 按模板查询
	 * @param categoryExtension
	 * @return
	 */
	List<CategoryExtensionDO> getCategoryExtensionsByTemplate(CategoryExtensionDO categoryExtension);
}
