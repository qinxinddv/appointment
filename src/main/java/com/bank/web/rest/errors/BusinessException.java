package com.bank.web.rest.errors;


public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -3047159547334306494L;

    private String code = null;

    public BusinessException(String code, String msg) {
        super(msg);
        this.code = code;
    }

    public BusinessException(String msg) {
        super(msg);
    }

    public String getCode() {
        return this.code;
    }

}
