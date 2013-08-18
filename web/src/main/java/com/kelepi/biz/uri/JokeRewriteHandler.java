package com.kelepi.biz.uri;

import com.alibaba.citrus.service.uribroker.interceptor.URIBrokerInterceptor;
import com.alibaba.citrus.service.uribroker.uri.URIBroker;
import com.alibaba.citrus.turbine.uribroker.uri.TurbineContentURIBroker;
import com.alibaba.citrus.turbine.uribroker.uri.TurbineURIBroker;
import com.alibaba.citrus.util.StringUtil;

/**
 *  重写前台页面url
 * @author
 * @create Apr 7, 2008
 */
public class JokeRewriteHandler implements URIBrokerInterceptor {



    public void perform(URIBroker broker) {
            TurbineURIBroker tBroker = (TurbineURIBroker) broker;
            String target = tBroker.getTarget();

          if ("homepage.vm".equals(target) && "2".equals(tBroker.getQueryData("recommendType"))) {
              tBroker.setComponentPath("hot");
              tBroker.removeQueryData("recommendType");
               if (!StringUtil.isEmpty(tBroker.getQueryData("page"))) {
                   tBroker.setContextPath(tBroker.getQueryData("page"));
                   tBroker.removeQueryData("page");
               }
              tBroker.setTarget(null);

          }

        if ("userCreation.vm".equals(target)) {
            tBroker.setComponentPath("/u/"+tBroker.getQueryData("userId"));
            tBroker.removeQueryData("userId");
            if (!StringUtil.isEmpty(tBroker.getQueryData("page"))) {
                tBroker.setContextPath(tBroker.getQueryData("page"));
                tBroker.removeQueryData("page");
            }
            tBroker.setTarget(null);
        }

        if ("userTop.vm".equals(target)) {
            tBroker.setComponentPath("/u/"+tBroker.getQueryData("userId")+"/t");
            tBroker.removeQueryData("userId");
            if (!StringUtil.isEmpty(tBroker.getQueryData("page"))) {
                tBroker.setContextPath(tBroker.getQueryData("page"));
                tBroker.removeQueryData("page");
            }
            tBroker.setTarget(null);
        }

        if ("jokeDetail.vm".equals(target)) {
            tBroker.setComponentPath("/t/"+tBroker.getQueryData("jokeId"));
            tBroker.removeQueryData("jokeId");
            tBroker.setTarget(null);
        }
    }
}