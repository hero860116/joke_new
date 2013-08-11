package com.kelepi.biz.ao;

import com.kelepi.common.bean.KeyValue;
import com.kelepi.dal.dataobject.CategoryDO;
import com.kelepi.dal.queryobject.CategoryQuery;

import java.util.List;


public interface CategoryAO {

    /**
     * 生成顶级类型
     * @param outId
     * @param outType
     * @return
     */
    long generateOutCategory(String outId, String outType);
	
	/**
	 * 保存
	 * @param category
	 * @return TODO
	 */
	long save(CategoryDO category);
	
	/**
	 * 更新
	 * @param category
	 */
	void update(CategoryDO category);
	
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
	CategoryDO getCategory(long id);
	
	/**
	 * 按模板查询
	 * @param category
	 * @return
	 */
	List<CategoryDO> getCategorysByTemplate(CategoryDO category);
	
	/**
	 * 分页查询
	 * @return
	 */
	List<CategoryDO> findCategorysByQuery(CategoryQuery categoryQuery);
	
	/**
	 * 更新排序
	 * @param indexf
	 * @param id
	 * @return
	 */
	void updateIndexf(int indexf, long id);
	
	/**
	 * 根据parentId获取子类别列表
	 * @param parentId
	 * @return
	 */
	List<KeyValue> getKeyValueFromCategorys(long parentId);

	/**
	 * 根据对象选中id获取出他的父子节点
	 * @param id 指定id的继承结构
	 * @param parentId 如果找不到指定id，则获取指定父id的结果集合
	 * @return
	 */
	//List<CategoryKeyValue> getCategoryKeyValueFromCategorys(long id, long parentId);
	
/*	*//**
	 * 获取指定大类，指定夫目录下的级联类别结构图
	 * @param parentId
	 * @param structureId
	 * @param structureType
	 * @return
	 *//*
	List<CategoryDO> getCascadeCategorys(long parentId, long structureId, int structureType);*/
	
	/**
	 * 将指定类别的类别管理级联加入缓存
	 */
	void putToCache();
	
	/**
	 */
	List<CategoryDO> getSubCategorys(Long parentId);

	/**
	 * structureId 与 structureType用来定位大类别缓存
	 *
     *
     * @param id
     * @return
	 */
	CategoryDO getCategoryFromCache(Long id);
	
	//获得顶级目录
	CategoryDO getTopCategory(long id);
	
	/**
	 * 根据实体对象列表，获取对应的层级结构，即从最上父类列表（id=0）一层层定位
	 * 到最下层类别，最下层类别再包含具体的实体，不包含数据的类别不显示
	 * 该结构中，categoryDO只包含子类列表，不包含父类类表
	 * @param <T>
	 * @param objs
	 * @param oppositeCategoryId
	 * @param structureId TODO
	 * @param structureType TODO
	 * @return
	 */
	<T> List<CategoryDO> getCategoryListFromEntitys(List<T> objs, String oppositeCategoryId, long structureId, int structureType);
	
	/**
	 * 获取指定id的应用id列表（即被实体引用的id），用于父类别过滤
	 * @return
	 */
	List<Long> getApplyIds(Long categoryId);
	
	/**
	 * 获取指定id的应用id列表（即被实体引用的id），用于父类别过滤
	 * @param structureId TODO
	 * @param structureType TODO
	 * @return
	 */
	List<Long> getApplyIdsByParent(Long parentId, Long structureId, Integer structureType);

}
