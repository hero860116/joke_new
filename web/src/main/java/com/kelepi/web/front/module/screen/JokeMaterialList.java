package com.kelepi.web.front.module.screen;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.alibaba.citrus.turbine.uribroker.uri.TurbineContentURIBroker;
import com.google.gson.Gson;
import com.kelepi.biz.ao.CategoryAO;
import com.kelepi.biz.ao.JokeMaterialAO;
import com.kelepi.biz.ao.PicMaterialAO;
import com.kelepi.common.bean.ParamInstance;
import com.kelepi.dal.dataobject.CategoryDO;
import com.kelepi.dal.dataobject.JokeMaterialDO;
import com.kelepi.dal.dataobject.PicMaterialDO;
import com.kelepi.dal.enums.MainStatus;
import com.kelepi.dal.jsonobject.*;
import com.kelepi.dal.queryobject.JokeMaterialQuery;
import com.kelepi.dal.queryobject.PicMaterialQuery;
import com.kelepi.web.common.BaseScreen;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * User: liWeiLin
 * Date: 13-8-14 下午9:18
 */
public class JokeMaterialList extends BaseScreen{

    @Autowired
    private JokeMaterialAO jokeMaterialAO;

    @Autowired
    private CategoryAO categoryAO;

    public void execute(@Param("categoryId")Long categoryId, @Param("currentPage")int currentPage, TurbineRunData rundata, Context context)
            throws Exception {
        rundata.setLayoutEnabled(false);

        //图片列表
        JokeMaterialQuery jokeMaterialQuery = new JokeMaterialQuery();
        jokeMaterialQuery.setCategoryId(categoryId);
        jokeMaterialQuery.setStatus(MainStatus.NORMAL.getType());
        jokeMaterialQuery.setCurrentPage(currentPage);
        jokeMaterialQuery.setPageSize(10);
        List<JokeMaterialDO> jokeMaterialDOs = jokeMaterialAO.findJokeMaterialsByQuery(jokeMaterialQuery);
        List<String> images = new ArrayList<String>();

        List<JokeTextJson> jokeTextJsons = new ArrayList<JokeTextJson>();
        for (JokeMaterialDO jokeMaterialDO :  jokeMaterialDOs) {
            JokeTextJson jokeTextJson = new JokeTextJson();
            jokeTextJson.setContent(jokeMaterialDO.getContent());
            jokeTextJson.setId(jokeMaterialDO.getId());
            jokeTextJsons.add(jokeTextJson);
        }

        //
        List<PageJson> pageJsons = new ArrayList<PageJson>();
        if (currentPage > 1) {
            PageJson pageJson = new PageJson();
            pageJson.setString("上一页");
            pageJson.setHref(getTurbineURIBroker("jokeModule").setTarget("jokeMaterialList.vm").addQueryData("categoryId", categoryId).addQueryData("currentPage", (currentPage - 1)).render());

            pageJsons.add(pageJson);
        }

        if (jokeMaterialQuery.getCurrentPage() < jokeMaterialQuery.getTotalPage()) {
            PageJson pageJson = new PageJson();
            pageJson.setString("下一页");
            pageJson.setHref(getTurbineURIBroker("jokeModule").setTarget("jokeMaterialList.vm").addQueryData("categoryId", categoryId).addQueryData("currentPage", (currentPage + 1)).render());

            pageJsons.add(pageJson);
        }

        JokeMaterialJson jokeMaterialJson = new JokeMaterialJson();
        jokeMaterialJson.setText(jokeTextJsons);
        jokeMaterialJson.setPage(pageJsons);

        context.put("data", new Gson().toJson(jokeMaterialJson));

    }
}
