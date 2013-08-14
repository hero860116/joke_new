package com.kelepi.web.common;

import com.alibaba.citrus.service.uribroker.URIBrokerService;
import com.alibaba.citrus.service.uribroker.uri.URIBroker;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.turbine.uribroker.uri.TurbineURIBroker;
import com.alibaba.citrus.turbine.util.TurbineUtil;
import com.kelepi.common.bean.JsonResult;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

public class BaseAction extends BaseSession{
	@Resource
	private URIBrokerService uriBrokerService;
	
	@Resource
	private HttpServletRequest request;

	protected TurbineURIBroker getTurbineURIBroker(String turbineUriName) {
		return (TurbineURIBroker)uriBrokerService.getURIBroker(turbineUriName);
	}
	
	@SuppressWarnings("unchecked")
	protected <T extends URIBroker> T getURIBroker(String uriName) {
		return (T)uriBrokerService.getURIBroker(uriName);
	}	
	
	/**
	 * @param message
	 */
	protected void redirectToUserError(String message) {
		TurbineRunData rundata = TurbineUtil.getTurbineRunData(request);
		rundata.setRedirectLocation(getTurbineURIBroker("commonModule").setTarget("userError.vm").addQueryData("v_param", "message").addQueryData("message", message).render());
	}

    protected void toSuccessJson(Object data) {
        JsonResult jsonResult = new JsonResult(data);
        TurbineRunData rundata = TurbineUtil.getTurbineRunData(request);
        rundata.getParameters().add("data", jsonResult);
        rundata.setRedirectTarget("json");
    }
}
