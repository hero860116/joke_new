package com.kelepi.dal.dao;

import com.kelepi.dal.dataobject.CategoryExtensionDO;

import java.util.List;
import java.util.Map;


public interface CategoryExtensionDAO {
	
	/**
	 * 保存
	 *
     * @param categoryExtensionDO                                                    s
	 */
	long save(CategoryExtensionDO categoryExtensionDO);
	
	/**
	 * 更新
     */
	void update(CategoryExtensionDO categoryExtensionDO);
	
	/**
	 * 删除
	 * @param id
	 */
	void delete(long id);
	
	/**
	 * 主键获取
	 * @param id
	 * @return
	 */
	CategoryExtensionDO getCategoryExtension(long id);
	
	/**
	 * 按模板查询
	 *
     * @param categoryExtensionDO
     * @return
	 */
	List<CategoryExtensionDO> getCategoryExtensionListByTemplate(CategoryExtensionDO categoryExtensionDO);

    /**
     * 根据cid列表获取属性列表结果
     * @param cids
     * @return
     */
    List<CategoryExtensionDO> getCategoryExtensions(List<Long> cids);

    /**
     * 根据cid获取属性类表
     * @param cid
     * @return
     */
    List<CategoryExtensionDO> getCategoryExtensions(long cid);

    /**
     * 根据cid获取属性类表
     * @param cid
     * @return
     */
    Map<String, String> getCategoryExtensionMap(long cid);
}
