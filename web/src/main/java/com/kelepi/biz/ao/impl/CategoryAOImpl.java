package com.kelepi.biz.ao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.kelepi.biz.ao.BaseAO;
import com.kelepi.biz.ao.CategoryAO;
import com.kelepi.common.bean.KeyValue;
import com.kelepi.common.bean.ParamInstance;
import com.kelepi.dal.dao.CategoryDAO;
import com.kelepi.dal.dataobject.CategoryDO;
import com.kelepi.dal.queryobject.CategoryQuery;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("categoryAO")
public class CategoryAOImpl extends BaseAO implements CategoryAO {

	@Resource
	private CategoryDAO categoryDAO;

    @Transactional
    public long generateOutCategory(String outId, String outType) {
        String outTypeName = outType + "_" + outId;
        CategoryDO categoryDO = new CategoryDO();
        categoryDO.setName(outTypeName);
        categoryDO.setParentId(0l);

        List<CategoryDO> categoryDOList = categoryDAO.getCategorysByTemplate(categoryDO);

        long id = 0;
        if (categoryDOList.size()  == 0) {
            if (categoryDO.getIndexf() == null) {
                categoryDO.setIndexf(100);
            }
            id = categoryDAO.save(categoryDO);
        }  else if (categoryDOList.size() == 1) {
            id =  categoryDOList.get(0).getId();
        }
        return id;
    }

    public long save(CategoryDO category) {
        if (category.getIndexf() == null) {
            category.setIndexf(100);
        }
		long id = categoryDAO.save(category);

        //插入成功，更新缓存
        if (id > 0) {
            CategoryDO categoryDO = getCategory(id);
            CategoryDO cacheCategoryDO = ParamInstance.getCategory(category.getParentId());
            cacheCategoryDO.getSubCategoryDOs().add(categoryDO);

            ParamInstance.addCategory(categoryDO);
        }

        return id;
	}

	public void update(CategoryDO category) {
        CategoryDO srcCategoryDO = categoryDAO.getCategory(category.getId());
        category.setIndexf(srcCategoryDO.getIndexf());
		categoryDAO.update(category);

        //更新成功，更新缓存
        srcCategoryDO = categoryDAO.getCategory(category.getId());
        CategoryDO cacheCategoryDO = ParamInstance.getCategory(category.getId());
        cacheCategoryDO.setName(srcCategoryDO.getName());
        cacheCategoryDO.setDescription(srcCategoryDO.getDescription());
        cacheCategoryDO.setIndexf(srcCategoryDO.getIndexf());
        cacheCategoryDO.setGmtModify(srcCategoryDO.getGmtModify());
	}

	public void delete(long id) {
		categoryDAO.delete(id);

        ParamInstance.getCategory(id).setIsDelete(1);
	}

	public CategoryDO getCategory(long id) {
		return categoryDAO.getCategory(id);
	}

	public List<CategoryDO> getCategorysByTemplate(CategoryDO category) {
		return categoryDAO.getCategorysByTemplate(category);
	}

	public List<CategoryDO> findCategorysByQuery(CategoryQuery categoryQuery) {
		return categoryDAO.findCategorysByQuery(categoryQuery);
	}

	/*
	 * @Override public List<CategoryDO> getCategorysByIds(List<Long> ids) {
	 * return categoryDAO.getCategorysByIds(ids); }
	 */

	public void updateIndexf(int indexf, long id) {
		categoryDAO.updateIndexf(indexf, id);

        ParamInstance.getCategory(id).setIndexf(indexf);
	}

	public CategoryDO getCategoryFromCache(Long id) {
		return ParamInstance.getCategory(id);
	}

	public List<CategoryDO> getSubCategorys(Long parentId) {
		return ParamInstance.getCategoryList(parentId);
	}

	public CategoryDO getTopCategory(long id) {
		CategoryDO categoryDO = ParamInstance.getCategory(id);

		if (categoryDO == null) {
			return null;
		}

		List<CategoryDO> parentCategoryDOs = categoryDO.getParentCategoryDOs();
		if (parentCategoryDOs.size() == 0) {
			return categoryDO;
		} else {
			return parentCategoryDOs.get(0);
		}
	}

	public <T> List<CategoryDO> getCategoryListFromEntitys(List<T> objs,
			String oppositeCategoryId, long structureId, int structureType) {
		Map<Long, CategoryDO> idCategoryMap = getIdCategoryMap(structureId, structureType);

		String start = oppositeCategoryId.substring(0, 1);
		String methodName = "get" + start.toUpperCase()
				+ oppositeCategoryId.substring(1);
		//聚合用
		Map<Long, List<CategoryDO>> parentIdToCategorysMap = new HashMap<Long, List<CategoryDO>>();

		//因为是从底层定位到上层，所以很可能出现重复的情况，这个map可以防止出现同一个类型在继承结构中出现多个的情况
		Map<Long, CategoryDO> haveAddCascadeMap = new HashMap<Long, CategoryDO>();
		try {
			for (T obj : objs) {
				Long categoryId = (Long) obj.getClass().getDeclaredMethod(methodName, null).invoke(obj, null);

				if (categoryId != null) {
					CategoryDO categoryDO = idCategoryMap.get(categoryId);
					CategoryDO copyCategory = new CategoryDO();
					copyCategory.setId(categoryDO.getId());
					copyCategory.setName(categoryDO.getName());
					copyCategory.setParentId(categoryDO.getParentId());

					//将自己加入别的子列表中
					List<CategoryDO> categoryList = parentIdToCategorysMap.get(copyCategory.getParentId());
					if (categoryList == null) {
						categoryList = new ArrayList<CategoryDO>();
						parentIdToCategorysMap.put(copyCategory.getParentId(), categoryList);
					}
					//防止重复加入
					if (haveAddCascadeMap.get(copyCategory.getId()) == null) {
						categoryList.add(copyCategory);
						haveAddCascadeMap.put(copyCategory.getId(), copyCategory);
					}

					//加入实体类列表
					List<Object> subEntityList = haveAddCascadeMap.get(copyCategory.getId()).getEntityList();
					subEntityList.add(obj);

					//对父类进行同样的处理
					for (CategoryDO categoryDO1 : categoryDO.getParentCategoryDOs()) {
						CategoryDO copyCategory1 = new CategoryDO();
						copyCategory1.setId(categoryDO1.getId());
						copyCategory1.setName(categoryDO1.getName());
						copyCategory1.setParentId(categoryDO1.getParentId());

						List<CategoryDO> categoryList1 = parentIdToCategorysMap.get(copyCategory1.getParentId());
						if (categoryList1 == null) {
							categoryList1 = new ArrayList<CategoryDO>();
							parentIdToCategorysMap.put(categoryDO1.getParentId(), categoryList1);
						}
						//防止重复加入
						if (haveAddCascadeMap.get(copyCategory1.getId()) == null) {
							categoryList1.add(copyCategory1);
							haveAddCascadeMap.put(copyCategory1.getId(), copyCategory1);
						}

						//构造自己的子类列表
						List<CategoryDO> subCategoryList1 = parentIdToCategorysMap.get(copyCategory1.getId());
						if (subCategoryList1 == null) {
							subCategoryList1 = new ArrayList<CategoryDO>();
							parentIdToCategorysMap.put(copyCategory1.getId(), subCategoryList1);
						}
						copyCategory1.setSubCategoryDOs(subCategoryList1);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return parentIdToCategorysMap.get(0l);
	}

	public List<Long> getApplyIds(Long categoryId) {
		List<Long> categoryIds = new ArrayList<Long>();
		
		CategoryDO categoryDO = ParamInstance.getCategory(categoryId);;
		
		if (categoryDO == null) {
			return new ArrayList<Long>();
		}
		
		addCategoryId(categoryIds, categoryDO);
		
		return categoryIds;
	}
	
	private void addCategoryId(List<Long> categoryIds, CategoryDO categoryDO) {
		List<CategoryDO> subCategoryDO = categoryDO.getSubCategoryDOs();
		
		if (subCategoryDO.size() == 0) {
			categoryIds.add(categoryDO.getId());
		} else {
			for (CategoryDO category : subCategoryDO) {
				addCategoryId(categoryIds, category);
			}
		}
	}

	public List<Long> getApplyIdsByParent(Long parentId, Long structureId, Integer structureType) {
		List<Long> categoryIds = new ArrayList<Long>();
		List<CategoryDO> subCategoyDOs = getSubCategorys(parentId);

		for (CategoryDO categoryDO : subCategoyDOs) {
			List<Long> subApplyIds = getApplyIds(categoryDO.getId());
			categoryIds.addAll(subApplyIds);
		}

		return categoryIds;
	}

	// 初始化缓存
	public void putToCache() {
		CategoryQuery categoryQuery = new CategoryQuery();
        categoryQuery.setFirstOrder("indexf");
        categoryQuery.setFirstOrderSort("asc");
		List<CategoryDO> categoryDOs = categoryDAO.findCategorysByQueryNoPage(categoryQuery);

		// 父类id -- 对应的子类列表
		Map<Long, List<CategoryDO>> parentCategorysMap = new HashMap<Long, List<CategoryDO>>();

		for (CategoryDO categoryDO : categoryDOs) {

			// 为当前类别设置子类别
			List<CategoryDO> subCategoryList = parentCategorysMap
					.get(categoryDO.getId());
			if (subCategoryList == null) {
				subCategoryList = new LinkedList<CategoryDO>();
				parentCategorysMap.put(categoryDO.getId(), subCategoryList);
			}
			categoryDO.setSubCategoryDOs(subCategoryList);

			// 将当前类别添加到父集合列表中
			List<CategoryDO> parentCategoryList = parentCategorysMap
					.get(categoryDO.getParentId());
			if (parentCategoryList == null) {
				parentCategoryList = new LinkedList<CategoryDO>();
				parentCategorysMap.put(categoryDO.getParentId(),
						parentCategoryList);
			}
			parentCategoryList.add(categoryDO);
		}

		// 获取顶层list
		List<CategoryDO> topCategoryDOs = parentCategorysMap.get(0l);
		List<CategoryDO> parentCategoryDOs = new ArrayList<CategoryDO>();

		// id -- 对应的category
		Map<Long, CategoryDO> idCategoryMap = new HashMap<Long, CategoryDO>();
		tianParentCategoryDOs(parentCategoryDOs, topCategoryDOs, idCategoryMap);

        ParamInstance.setIdMapCategory(idCategoryMap);
        ParamInstance.setParentMapCategory(parentCategorysMap);
	}

	private void tianParentCategoryDOs(List<CategoryDO> parentCategoryDOs,
			List<CategoryDO> topCategoryDOs, Map<Long, CategoryDO> idCategoryMap) {
		for (CategoryDO topCategoryDO : topCategoryDOs) {
			topCategoryDO.setParentCategoryDOs(parentCategoryDOs);
			idCategoryMap.put(topCategoryDO.getId(), topCategoryDO);
			List<CategoryDO> nextParentCategoryDOs = new ArrayList<CategoryDO>();
			nextParentCategoryDOs.addAll(parentCategoryDOs);
			nextParentCategoryDOs.add(topCategoryDO);
			tianParentCategoryDOs(nextParentCategoryDOs, topCategoryDO.getSubCategoryDOs(), idCategoryMap);
		}
	}

	private Map<Long, CategoryDO> getIdCategoryMap(long structureId,
			int structureType) {
		return null;
	}

	private Map<Long, List<CategoryDO>> getParentCategoryDOs(long structureId,
			int structureType) {
		return null;
	}

    public List<KeyValue> getKeyValueFromCategorys(long parentId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
