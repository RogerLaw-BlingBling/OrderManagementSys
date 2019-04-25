package com.ordersys.comon;

/**
 * Created by admin on 2019/1/7.
 */
public class CommResult {
    private Integer code;
    private String message;
    private boolean success;
    private Object data;

    public CommResult(Object o) {
        this(RESULTMESSAGE.SUCCESS);
        data = o;
    }

    public CommResult() {
        this(RESULTMESSAGE.SUCCESS);
    }

    public CommResult(RESULTMESSAGE message) {
        this.code = message.getCode();
        this.message = message.getMessage();
        if(this.code ==200)
            success = true;
        else
            success = false;
    }

    public CommResult(Integer code, String message) {
        this.code = code;
        this.message = message;
        if(this.code ==200)
            success = true;
        else
            success = false;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
