package com.kelepi.web.front.module.screen;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.alibaba.citrus.turbine.uribroker.uri.TurbineContentURIBroker;
import com.google.gson.Gson;
import com.kelepi.biz.ao.CategoryAO;
import com.kelepi.biz.ao.PicMaterialAO;
import com.kelepi.common.bean.ParamInstance;
import com.kelepi.dal.dataobject.CategoryDO;
import com.kelepi.dal.dataobject.PicMaterialDO;
import com.kelepi.dal.enums.MainStatus;
import com.kelepi.dal.jsonobject.PageJson;
import com.kelepi.dal.jsonobject.PicMaterialJson;
import com.kelepi.dal.jsonobject.RoleJson;
import com.kelepi.dal.queryobject.PicMaterialQuery;
import com.kelepi.util.ListUtil;
import com.kelepi.web.common.BaseScreen;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * User: liWeiLin
 * Date: 13-8-14 下午9:18
 */
public class PicMaterialList extends BaseScreen{

    @Autowired
    private PicMaterialAO picMaterialAO;

    @Autowired
    private CategoryAO categoryAO;

    public void execute(@Param("roleId")Long roleId, @Param("seriesId")Long seriesId, @Param("currentPage")int currentPage, TurbineRunData rundata, Context context)
            throws Exception {
        rundata.setLayoutEnabled(false);

        //图片列表
        PicMaterialQuery picMaterialQuery = new PicMaterialQuery();
        picMaterialQuery.setRoleId(roleId);
        picMaterialQuery.setSeriesId(seriesId);
        picMaterialQuery.setStatus(MainStatus.NORMAL.getType());
        picMaterialQuery.setCurrentPage(currentPage);
        List<PicMaterialDO> picMaterialDOs = picMaterialAO.findPicMaterialsByQuery(picMaterialQuery);
        List<String> images = new ArrayList<String>();

        TurbineContentURIBroker turbineContentUri = (TurbineContentURIBroker)getURIBroker("upyunImageServer");
        for (PicMaterialDO picMaterialDO :  picMaterialDOs) {

            images.add(turbineContentUri.getURI(picMaterialDO.getImageUrl()).render());
        }

        //获得角色列表
        List<CategoryDO> roleCategoryDOs =   ParamInstance.getCategory(seriesId).getSubCategorys();
        List<RoleJson> roleJsons = new ArrayList<RoleJson>();
        for (CategoryDO categoryDO : roleCategoryDOs) {
            RoleJson roleJson = new RoleJson();
            roleJson.setId(categoryDO.getId());
            roleJson.setRolename(categoryDO.getName());
            roleJson.setHref(getTurbineURIBroker("jokeModule").setTarget("picMaterialList").addQueryData("roleId", roleJson.getId()).addQueryData("seriesId", seriesId).render());

            roleJsons.add(roleJson);
        }

        //
        List<PageJson> pageJsons = new ArrayList<PageJson>();
        if (currentPage > 1) {
            PageJson pageJson = new PageJson();
            pageJson.setString("上一页");
            pageJson.setHref(getTurbineURIBroker("jokeModule").setTarget("picMaterialList").addQueryData("roleId", roleId).addQueryData("seriesId", seriesId).addQueryData("currentPage", (currentPage - 1)).render());

            pageJsons.add(pageJson);
        }

        if (currentPage < picMaterialQuery.getTotalPage()) {
            PageJson pageJson = new PageJson();
            pageJson.setString("下一页");
            pageJson.setHref(getTurbineURIBroker("jokeModule").setTarget("picMaterialList").addQueryData("roleId", roleId).addQueryData("seriesId", seriesId).addQueryData("currentPage", (currentPage + 1)).render());

            pageJsons.add(pageJson);
        }

        PicMaterialJson picMaterialJson = new PicMaterialJson();
        picMaterialJson.setImg(images);
        picMaterialJson.setPage(pageJsons);
        picMaterialJson.setRole(roleJsons);

        context.put("data", new Gson().toJson(picMaterialJson));

    }
}
