package com.kelepi.biz.manager;

import com.kelepi.common.bean.Result;
import com.kelepi.common.bean.ResultSupport;

/**
 * User: liWeiLin
 * Date: 13-8-4 下午9:24
 */
public class BaseManager {
    public Result createResult() {
        return new ResultSupport(Boolean.FALSE);
    }

    public Result createResult(boolean isSucced) {
        return new ResultSupport(isSucced);
    }
}
