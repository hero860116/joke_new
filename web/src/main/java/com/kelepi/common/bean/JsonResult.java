package com.kelepi.common.bean;

public class JsonResult {
    boolean success;
    String msg;
    Object data;

    public JsonResult(Object data) {
        success = true;
        this.data = data;
    }

    public JsonResult(String msg) {
        success=false;
        this.msg = msg;
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

    public boolean isSuccess() {

        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
