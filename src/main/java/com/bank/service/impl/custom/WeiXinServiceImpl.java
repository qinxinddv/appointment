package com.bank.service.impl.custom;

import com.bank.common.HttpsClientRequestFactory;
import com.bank.service.custom.WeiXinService;
import com.bank.service.dto.custom.SessionKeyDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class WeiXinServiceImpl implements WeiXinService {
    public static final String WX_SESSION_URL = "https://api.weixin.qq.com/sns/jscode2session";
    public static final String APP_ID = "wx902ccbd7905144ea";
    public static final String SECRET = "fc908bc8c6bb896678c9ea318a1c1e5d";
    public final static Logger log = LoggerFactory.getLogger(WeiXinServiceImpl.class);

    @Override
//    @Cacheable(cacheNames = {"session-key"},key = "#root.methodName+'['+#sessionKey+']'")
    public SessionKeyDto getSessionKey(String sessionKey, String code) {
        RestTemplate restTemplate = new RestTemplate(new HttpsClientRequestFactory());
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM, MediaType.TEXT_PLAIN));
        restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);
        String url = WX_SESSION_URL+"?appid="+APP_ID+"&secret="+SECRET+"&grant_type=authorization_code"+"&js_code="+code;
        log.debug("开始获取微信SessionKey,URL:{}",url);
        SessionKeyDto dto = restTemplate.postForObject(url,null,SessionKeyDto.class);
        log.debug("结束获取微信SessionKey,URL:{},返回结果:{}",url,dto);
        if(dto.getErrcode() != null){

            throw new RuntimeException("获取微信SessionKey异常，请求："+url+"  ;返回："+dto);
        }
        return dto;
    }
}
