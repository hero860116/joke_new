package com.kelepi.web.admin.module.screen;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.google.gson.Gson;
import com.kelepi.dal.enums.MainStatus;
import com.kelepi.dal.jsonobject.TreeNode;
import com.kelepi.web.common.BaseScreen;

public class QuiLeft extends BaseScreen {


    public void execute(Navigator nav, TurbineRunData rundata, Context context) throws Exception {

        rundata.setLayoutEnabled(false);

        List<TreeNode> treeNodes = new ArrayList<TreeNode>();

        TreeNode treeNode4 = new TreeNode(0l, "笑话管理", getTurbineURIBroker("adminModule").setTarget("jokeList.vm").render(), "frmright");


        TreeNode treeNode1 = new TreeNode(0l, "笑话类别", getTurbineURIBroker("adminModule").setTarget("generator.vm").addQueryData("action", "category_action").addQueryData("event_submit_do_generate_out_category", 1).addQueryData("outType", "jokeType").addQueryData("outId", "0").render(), "frmright");

        TreeNode treeNode2 = new TreeNode(0l, "笑话TAG", getTurbineURIBroker("adminModule").setTarget("generator.vm").addQueryData("action", "category_action").addQueryData("event_submit_do_generate_out_category", 1).addQueryData("outType", "jokeTag").addQueryData("outId", "0").render(), "frmright");

        TreeNode treeNode3 = new TreeNode(1l, 0l, "素材管理", true, null, null);
        TreeNode treeNode5 = new TreeNode(1l, "剧集管理", getTurbineURIBroker("adminModule").setTarget("generator.vm").addQueryData("action", "category_action").addQueryData("event_submit_do_generate_out_category", 1).addQueryData("outType", "material_series").addQueryData("outId", "0").render(), "frmright");
        TreeNode treeNode6 = new TreeNode(1l, "未审核图片", getTurbineURIBroker("adminModule").setTarget("picMaterialList.vm").addQueryData("status", MainStatus.TO_REVIEW.getType()).render(), "frmright");
        TreeNode treeNode7 = new TreeNode(1l, "已审核图片", getTurbineURIBroker("adminModule").setTarget("picMaterialList.vm").addQueryData("status", MainStatus.NORMAL.getType()).render(), "frmright");
        TreeNode treeNode8 = new TreeNode(1l, "笑话类别管理", getTurbineURIBroker("adminModule").setTarget("generator.vm").addQueryData("action", "category_action").addQueryData("event_submit_do_generate_out_category", 1).addQueryData("outType", "material_joke_content").addQueryData("outId", "0").render(), "frmright");
        TreeNode treeNode9 = new TreeNode(1l, "未审核笑话", getTurbineURIBroker("adminModule").setTarget("jokeMaterialList.vm").addQueryData("status", MainStatus.TO_REVIEW.getType()).render(), "frmright");
        TreeNode treeNode10 = new TreeNode(1l, "已审核笑话", getTurbineURIBroker("adminModule").setTarget("jokeMaterialList.vm").addQueryData("status", MainStatus.NORMAL.getType()).render(), "frmright");


        TreeNode treeNode100 = new TreeNode(0l, "刷新类别缓存", getTurbineURIBroker("adminModule").setTarget("generator.vm").addQueryData("action", "category_action").addQueryData("event_submit_do_put_to_cache", 1).render(), "frmright");

        treeNodes.add(treeNode4);
        treeNodes.add(treeNode1);
        treeNodes.add(treeNode2);
        treeNodes.add(treeNode3);
        treeNodes.add(treeNode5);
        treeNodes.add(treeNode6);
        treeNodes.add(treeNode7);
        treeNodes.add(treeNode8);
        treeNodes.add(treeNode9);
        treeNodes.add(treeNode10);
        treeNodes.add(treeNode100);
        context.put("treeNodesJson", new Gson().toJson(treeNodes));
    }
}
