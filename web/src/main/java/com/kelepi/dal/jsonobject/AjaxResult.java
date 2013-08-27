package com.kelepi.dal.jsonobject;

/**
 * User: liWeiLin
 * Date: 13-8-27 下午11:48
 */
public class AjaxResult {
    private boolean success;
    private String msg;
    private Object data;

    public AjaxResult(){}

    public static AjaxResult newSuccessResult(Object data) {
        AjaxResult result = new AjaxResult();
        result.setSuccess(true);
        result.setData(data);

        return result;
    }

    public static AjaxResult newFailureResult(String msg) {
        AjaxResult result = new AjaxResult();
        result.setSuccess(false);
        result.setMsg(msg);

        return result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
