package com.kelepi.web.front.module.screen;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.alibaba.citrus.turbine.uribroker.uri.TurbineContentURIBroker;
import com.google.gson.Gson;
import com.kelepi.common.bean.Result;
import com.kelepi.dal.jsonobject.AjaxResult;
import com.kelepi.web.common.BaseScreen;
import com.kelepi.biz.manager.ImageManager;
import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * User: liWeiLin
 * Date: 13-8-4 下午9:39
 */
public class UploadImg extends BaseScreen {


    @Autowired
    private ImageManager imageManager;

    @Autowired
    private  HttpServletRequest request;

    public void execute(@Param("type")String type, Navigator nav, TurbineRunData rundata, Context context)  {
        rundata.setLayoutEnabled(false);
        FileItem picture = rundata.getParameters().getFileItem("imgFile");

        String url = "";

        if ("faceimage".equals(type)) {
            url = imageManager.saveFaceImageUrl(request, picture);
            context.put("url", url);
        } else if ("jokeimage".equals(type)) {
            url = imageManager.saveJokeImageUrl(request, picture);
            context.put("url", url);
        }  else if ("materialimage".equals(type)) {

            Result result =    imageManager.saveMaterialImageUrl(request, picture);

            if (result.isSuccess()) {
                context.put("url", new Gson().toJson(AjaxResult.newSuccessResult(result.getModule("url"))));
            } else {
                context.put("url", new Gson().toJson(AjaxResult.newFailureResult((String)result.getModule("msg"))));
            }
        }

    }

}
