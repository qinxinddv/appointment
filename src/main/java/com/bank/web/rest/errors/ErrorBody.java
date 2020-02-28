package com.bank.web.rest.errors;

public class ErrorBody {
    private String msg;

    public ErrorBody(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
