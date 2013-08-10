package com.kelepi.web.admin.pipeline;

import static com.alibaba.citrus.turbine.util.TurbineUtil.getTurbineRunData;

import java.util.LinkedList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.alibaba.citrus.service.pipeline.PipelineContext;
import com.alibaba.citrus.service.pipeline.support.AbstractValve;
import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.TurbineRunDataInternal;

/**
 *
 * @author Administrator
 *
 *
 * 烦点：通过request获取完整的访问url
 */
public class RefererValve extends AbstractValve {

    @Resource
    private HttpServletRequest request;

    @Resource
    private HttpSession session;

    public void invoke(PipelineContext pipelineContext) throws Exception {

        String port = request.getServerPort() == 80 ? "" : ":"
                + (request.getServerPort());
        String queryString = request.getQueryString() == null ? "" : "?"
                + request.getQueryString();

        // 当前点击的url
        String inUri = "http://" + request.getServerName() + port
                + request.getRequestURI() + queryString;

        // 当前refererurl
        String referUri = request.getHeader("referer");

        // session中保存的url
        LinkedList<String> referers = (LinkedList<String>) session
                .getAttribute("referers");

        //是否增加referer
        if (referUri == null) {

        } else if (inUri.contains("success")) {

        }
        //提交当前页面
        else if (inUri.equals(referUri)) {

        }
        //当前页面的分页提交
        else if (referers != null && referers.size() > 0
                && removePageQuery(inUri).equals(removePageQuery(referUri))) {

        }

        else {

            if (referers == null) {
                referers = new LinkedList<String>();
            }

            referers.addLast(referUri);

            // 将上一个referer加入后，检查连接是否在原来的记录中，然后进行重置
            if (referers != null && referers.contains(inUri)) {
                int index = referers.indexOf(inUri);

                LinkedList<String> subReferers = new LinkedList<String>();
                for (int i = 0; i < index; i++) {
                    subReferers.add(referers.get(i));
                }

                referers = subReferers;
            }
        }

        //是否删除referer
        if (referers != null && referers.size() > 0
                && inUri.equals(referers.getLast())) {
            referers.removeLast();

        }

        TurbineRunDataInternal rundata = (TurbineRunDataInternal) getTurbineRunData(request);
        Context context = rundata.getContext();

        session.setAttribute("referers", referers);
        if (referers != null && referers.size() > 0) {
            context.put("referer", referers.getLast());
            rundata.getParameters().add("referer", referers.getLast());
        }

        pipelineContext.invokeNext();
    }

    private String removePageQuery(String uri) {
        String removeUri = "";
        if (uri.contains("&page=")) {
            removeUri = uri.replaceAll("&page=\\d+", "");
        } else {
            removeUri = uri.replaceAll("page=\\d+", "");
        }

        if (removeUri.endsWith("?")) {
            removeUri.replace("?", "");
        }

        return removeUri;
    }
}