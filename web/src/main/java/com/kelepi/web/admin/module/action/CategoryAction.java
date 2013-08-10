package com.kelepi.web.admin.module.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.turbine.dataresolver.FormGroup;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.kelepi.biz.ao.CategoryAO;
import com.kelepi.dal.dataobject.CategoryDO;
import com.kelepi.web.common.BaseAction;

/**
 * 
 * @author Administrator
 *
 */
public class CategoryAction extends BaseAction {

    @Resource
    private HttpServletRequest request;
	
	@Resource
	private CategoryAO categoryAO;

    public void doGenerateOutCategory(@Param("outId") String outId, @Param("outType")String outType, Navigator nav, TurbineRunData rundata, Context context) {

        long id = categoryAO.generateOutCategory(outId, outType);

        String rediUrl = getTurbineURIBroker("adminModule").setTarget("categoryList.vm").addQueryData("parentId", id).render();

        nav.redirectToLocation(rediUrl);
    }

	/**
	 * 
	 * 
	 * @param rundata
	 * @param context
	 */
	public void doSave(@FormGroup("addModifyCategory") CategoryDO categoryDO, Navigator nav, TurbineRunData rundata, Context context) {
		categoryAO.save(categoryDO);
		
		rundata.setRedirectLocation(getTurbineURIBroker("adminModule").setTarget("categoryList.vm").addQueryData("parentId", categoryDO.getParentId()).render());
	}
	
	/**
	 * 
	 * @param rundata
	 * @param context
	 */
	public void doDelete(@Param("id") Long id, @Param("parentId") Long parentId, @Param("referer")String referer, Navigator nav, TurbineRunData rundata, Context context) {
		categoryAO.delete(id);
        rundata.setRedirectLocation(getTurbineURIBroker("adminModule").setTarget("categoryList.vm").addQueryData("parentId", parentId).render());
	}
	
	/**
	 * 
	 * 
	 * @param rundata
	 * @param context
	 */
	public void doUpdate(@FormGroup("addModifyCategory") CategoryDO categoryDO, Navigator nav, TurbineRunData rundata, Context context) {
		categoryAO.update(categoryDO);
        rundata.setRedirectLocation(getTurbineURIBroker("adminModule").setTarget("categoryList.vm").addQueryData("parentId", categoryDO.getParentId()).render());
	}
	
	/**
	 * 更新排序
	 * @param rundata
	 * @param context
	 */
	public void doUpdateIndexf(@Param("indexf") Integer indexf, @Param("id") Long id, @Param("referer")String referer, Navigator nav, TurbineRunData rundata, Context context) {
		
		if(indexf != null && id != null) {
			categoryAO.updateIndexf(indexf, id);
		}
		
		rundata.setRedirectLocation(referer);
	}

	public void doPutToCache(@Param("referer")String referer, Navigator nav, TurbineRunData rundata, Context context) {
		
		long s = System.currentTimeMillis();
		categoryAO.putToCache();
		long e = System.currentTimeMillis();
		System.out.println("PutToCache cost :" + (e - s) + "ms");
		
		rundata.setRedirectLocation(referer);
		
	}
}
