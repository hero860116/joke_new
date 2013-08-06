package com.kelepi.web.front.module.action;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.kelepi.biz.ao.SinaWeiboAO;
import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * User: liWeiLin
 * Date: 13-8-4 下午9:39
 */
public class UploadAction {

    public void doUploadImg(Navigator nav, TurbineRunData rundata, Context context)  {
        FileItem picture = rundata.getParameters().getFileItem("imgFile");
        System.out.println(picture);
    }

}
