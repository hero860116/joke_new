package com.kelepi.common.bean;

import com.kelepi.dal.dataobject.CategoryDO;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ParamInstance {

    private static final String PARENT_MAP_CATEGORY = "parent_map_category";

    private static final String ID_MAP_CATEGORY = "id_map_category";
	
	private Map<String, Object> paras = new ConcurrentHashMap<String, Object>();

	private static ParamInstance instance = new ParamInstance();
	
	
	private ParamInstance() {
		
	}
	
	public static ParamInstance getInstance() {
		return instance;
	}
	

	public static Object get(String key) {
		return ParamInstance.getInstance().getParas().get(key);
	}
	
	public static void set(String key, Object value) {
		ParamInstance.getInstance().getParas().put(key, value);
	}

    public static void setParentMapCategory(Map<Long, List<CategoryDO>> parentCategorysMap) {
        ParamInstance.getInstance().getParas().put(PARENT_MAP_CATEGORY, parentCategorysMap);
    }

    public static void setIdMapCategory(Map<Long, CategoryDO> idCategoryMap ) {
        ParamInstance.getInstance().getParas().put(ID_MAP_CATEGORY, idCategoryMap);
    }

    public static List<CategoryDO> getCategoryList(long parentId) {
        Map<Long, List<CategoryDO>> parentCategorysMap = (Map<Long, List<CategoryDO>>)ParamInstance.getInstance().getParas().get(PARENT_MAP_CATEGORY);

        return  parentCategorysMap.get(parentId);
    }

    public static CategoryDO getCategory(long id) {
        Map<Long, CategoryDO> idCategoryMap  = (Map<Long, CategoryDO>)ParamInstance.getInstance().getParas().get(ID_MAP_CATEGORY);

        return  idCategoryMap.get(id);
    }

	public Map<String, Object> getParas() {
		return paras;
	}
	
}
