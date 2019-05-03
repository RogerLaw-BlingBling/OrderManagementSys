package com.ordersys.commons;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RexModel<T> {

    private String error;
    private String message;
    private T data;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public RexModel<T> withMessage(String message) {
        this.message = message;
        return this;
    }

    public RexModel<T> withError(String error) {
        this.error = error;
        return this;
    }

    public RexModel<T> withData(T data) {
        this.data = data;
        return this;
    }
}
