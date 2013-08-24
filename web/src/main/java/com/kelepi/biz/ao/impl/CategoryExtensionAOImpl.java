package com.kelepi.biz.ao.impl;

import com.kelepi.biz.ao.BaseAO;
import com.kelepi.biz.ao.CategoryExtensionAO;
import com.kelepi.dal.dao.CategoryExtensionDAO;
import com.kelepi.dal.dataobject.CategoryExtensionDO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("categoryExtensionAO")
public class CategoryExtensionAOImpl extends BaseAO implements CategoryExtensionAO {

	@Resource
	private CategoryExtensionDAO categoryExtensionDAO;

    public long save(CategoryExtensionDO categoryExtension) {
		return categoryExtensionDAO.save(categoryExtension);
	}

	public void update(CategoryExtensionDO categoryExtension) {
		categoryExtensionDAO.update(categoryExtension);
	}

	public void delete(long id) {
		categoryExtensionDAO.delete(id);
	}

	public CategoryExtensionDO getCategoryExtension(long id) {
		return categoryExtensionDAO.getCategoryExtension(id);
	}

	public List<CategoryExtensionDO> getCategoryExtensionsByTemplate(CategoryExtensionDO categoryExtension) {
		return categoryExtensionDAO.getCategoryExtensionListByTemplate(categoryExtension);
	}

}
