package com.bank.service.dto.custom;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

public class SessionKeyDto implements Serializable {
    private static final long serialVersionUID = 1L;
    @JsonIgnoreProperties(ignoreUnknown=true)
    private String session_key;
    @JsonIgnoreProperties(ignoreUnknown=true)
    private String openid;
    @JsonIgnoreProperties(ignoreUnknown=true)
    private String errcode;
    @JsonIgnoreProperties(ignoreUnknown=true)
    private String errmsg;

    public String getSession_key() {
        return session_key;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    @Override
    public String toString() {
        return "SessionKeyDto{" +
            "session_key='" + session_key + '\'' +
            ", openid='" + openid + '\'' +
            ", errcode='" + errcode + '\'' +
            ", errmsg='" + errmsg + '\'' +
            '}';
    }
}
