package com.kelepi.web.admin.module.screen;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.google.gson.Gson;
import com.kelepi.dal.jsonobject.TreeNode;
import com.kelepi.web.common.BaseScreen;

public class QuiLeft extends BaseScreen {

	
	public void execute(Navigator nav, TurbineRunData rundata, Context context) throws Exception{
	    
	       rundata.setLayoutEnabled(false);
	    
	       List<TreeNode> treeNodes = new ArrayList<TreeNode>();
	       
	       TreeNode treeNode1 = new TreeNode(0l, "笑话类别", getTurbineURIBroker("adminModule").setTarget("generator.vm").addQueryData("action", "category_action").addQueryData("event_submit_do_generate_out_category", 1).addQueryData("outType", "jokeType").addQueryData("outId", "0").render(), "frmright");
	       
	       TreeNode treeNode2 = new TreeNode(0l, "笑话TAG", getTurbineURIBroker("adminModule").setTarget("generator.vm").addQueryData("action", "category_action").addQueryData("event_submit_do_generate_out_category", 1).addQueryData("outType", "jokeTag").addQueryData("outId", "0").render(), "frmright");

           treeNodes.add(treeNode1);
           treeNodes.add(treeNode2);
	       context.put("treeNodesJson", new Gson().toJson(treeNodes));
	}
}
