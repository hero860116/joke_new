package com.kelepi.biz.ao;

import com.kelepi.common.bean.Result;
import com.kelepi.common.bean.ResultSupport;
import com.kelepi.web.common.BaseSession;

/**
 * User: liWeiLin
 * Date: 13-8-4 下午9:37
 */
public class BaseAO extends BaseSession{

    public Result createResult() {
        return new ResultSupport(Boolean.FALSE);
    }

    public Result createResult(boolean isSucced) {
        return new ResultSupport(isSucced);
    }
}
