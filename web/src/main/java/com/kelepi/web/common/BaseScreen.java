package com.kelepi.web.common;

import com.alibaba.citrus.service.uribroker.URIBrokerService;
import com.alibaba.citrus.service.uribroker.uri.URIBroker;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.turbine.uribroker.uri.TurbineURIBroker;
import com.alibaba.citrus.turbine.util.TurbineUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

public class BaseScreen {
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
}
