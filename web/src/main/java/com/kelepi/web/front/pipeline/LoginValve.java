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

        if (request.getRequestURI().contains("add_topic.htm") || request.getRequestURI().contains("joke_review.htm") || request.getRequestURI().contains("pic_material_upload.htm")) {
            if (session.getAttribute("currentLoginUser") == null) {
                TurbineRunData rundata = TurbineUtil.getTurbineRunData(request);
                TurbineURIBroker jokeModule = (TurbineURIBroker)uriBrokerService.getURIBroker("jokeModule");

                if (request.getRequestURI().contains("add_topic.htm")) {
                    rundata.setRedirectLocation(jokeModule.setTarget("login.vm").addQueryData("index", "addTopic").render());
                } else if (request.getRequestURI().contains("joke_review.htm")) {
                    rundata.setRedirectLocation(jokeModule.setTarget("login.vm").addQueryData("index", "review").render());
                }  else if (request.getRequestURI().contains("pic_material_upload.htm")) {
                    rundata.setRedirectLocation(jokeModule.setTarget("login.vm").addQueryData("index", "material").render());
                }

                rundata.setRedirectLocation(jokeModule.setTarget("login.vm").addQueryData("index", "addTopic").render());

                return;
            }
        }

        pipelineContext.invokeNext();
    }

}
