package com.kelepi.web.admin.pipeline;

import com.alibaba.citrus.service.pipeline.PipelineContext;
import com.alibaba.citrus.service.pipeline.support.AbstractValve;
import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.TurbineRunDataInternal;
import com.kelepi.dal.dataobject.UserDO;
import com.kelepi.dal.enums.PermissionsType;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedList;

import static com.alibaba.citrus.turbine.util.TurbineUtil.getTurbineRunData;

/**
 *
 * @author Administrator
 *
 *
 * 烦点：通过request获取完整的访问url
 */
public class PermissionsValve extends AbstractValve {

    @Resource
    private HttpServletRequest request;

    @Resource
    private HttpSession session;

    public void invoke(PipelineContext pipelineContext) throws Exception {

        UserDO currentLoginUserDO = (UserDO)session.getAttribute("currentLoginUser");

        if (currentLoginUserDO == null) {
            //抛出异常到登录
            return;

        }  else if (currentLoginUserDO.getPermissions() != PermissionsType.ADMIN_MANAGE.getType()) {
            //抛出异常到无权限页面
            return;
        }

        pipelineContext.invokeNext();
    }
}