package com.bank.service.custom;

import com.bank.service.dto.custom.SessionKeyDto;

/**
 * 微信相关功能
 */
public interface WeiXinService {
    public SessionKeyDto getSessionKey(String sessionKey,String code);
}
