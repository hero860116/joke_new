package com.kelepi.biz.tool;

import com.alibaba.citrus.service.uribroker.URIBrokerService;
import com.alibaba.citrus.turbine.uribroker.uri.TurbineContentURIBroker;
import com.kelepi.common.bean.ParamInstance;
import com.kelepi.dal.dataobject.CategoryDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: liWeiLin
 * Date: 13-8-10 下午6:29
 */
@Component
public class ImageTool {

    @Autowired
    private URIBrokerService uriBrokerService;


    public String getURI(String rootUrl, String version) {

        if (rootUrl == null) {
            return "";
        }
        if (rootUrl.startsWith("http://")) {
            return rootUrl;
        } else {
            TurbineContentURIBroker turbineContentURIBroker = (TurbineContentURIBroker)uriBrokerService.getURIBroker("upyunImageServer");
            return turbineContentURIBroker.getURI(rootUrl).render() + "!" + version;
        }
    }
}
