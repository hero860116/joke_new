package com.kelepi.web.front.pipeline;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.alibaba.citrus.service.pipeline.PipelineContext;
import com.alibaba.citrus.service.pipeline.support.AbstractValve;
import com.alibaba.citrus.service.uribroker.URIBrokerService;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.turbine.uribroker.uri.TurbineURIBroker;
import com.alibaba.citrus.turbine.util.TurbineUtil;

/**
 * 如果没登陆跳转到登陆页面
 *
 * @author liweilin
 *
 */
public class LoginValve extends AbstractValve {

    @Resource
    private URIBrokerService uriBrokerService;

    @Resource
    private HttpServletRequest request;

    @Resource
    private HttpSession session;

    public void invoke(PipelineContext pipelineContext) throws Exception {

        //如果请求的是静态资源,不走valve
        if (request.getRequestURI().contains("user_address_list.htm") || request.getRequestURI().contains("modify_password.htm") || request.getRequestURI().contains("recent_order_list.htm")) {
            if ((Long)session.getAttribute("userId") == null) {
                TurbineRunData rundata = TurbineUtil.getTurbineRunData(request);
                TurbineURIBroker tasteModule = (TurbineURIBroker)uriBrokerService.getURIBroker("tasteModule");
                rundata.setRedirectLocation(tasteModule.setTarget("login.vm").render());

                return;
            }
        }

        pipelineContext.invokeNext();
    }

}
