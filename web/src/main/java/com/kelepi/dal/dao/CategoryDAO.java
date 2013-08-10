package com.kelepi.dal.dao;

import com.kelepi.dal.dataobject.CategoryDO;
import com.kelepi.dal.queryobject.CategoryQuery;

import java.util.List;


public interface CategoryDAO {
	
	/**
	 * 保存
	 * @param CategoryDO
	 * @return TODO
	 */
	long save(CategoryDO CategoryDO);
	
	/**
	 * 更新
	 * @param CategoryDO
	 */
	void update(CategoryDO CategoryDO);
	
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
	CategoryDO getCategory(long id);
	
	/**
	 * 按模板查询
	 * @param CategoryDO
	 * @return
	 */
	List<CategoryDO> getCategorysByTemplate(CategoryDO CategoryDO);
	
	
	/**
	 * 分页查询
	 * @param categoryQuery
	 * @return
	 */
	List<CategoryDO> findCategorysByQuery(CategoryQuery categoryQuery);

    /**
     * 不过滤删除订单，放入内存中，方便删除的依然可以正确使用
     * @param categoryQuery
     * @return
     */
    List<CategoryDO> findCategorysByQueryNoPage(CategoryQuery categoryQuery);
	
	/**
	 * 更新排序
	 * @param indexf
	 * @param id
	 * @return
	 */
	int updateIndexf(int indexf, long id);
	
	/**
	 * 根据parentId获取子类别列表
	 * @param parentId
	 * @return
	 */
	List<CategoryDO> getCategorysByParentId(long parentId);
	
	/**
	 * 根据类别id列表获取类别列表
	 * @param ids
	 * @return
	 */
	List<CategoryDO> getCategorysByIds(List<Long> ids);
}
