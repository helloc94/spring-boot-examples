package com.neo.facade;

import com.neo.mapper.SecretMapper;
import com.neo.model.Secret;
import com.neo.utils.StringUtils;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

@Component
public class DemoFacade {
	
	/**
	 * 
	 */
	private static final String TEMPSTR = "";

    /**
     * secretMapper
     * @return
     */
    @Autowired
    private SecretMapper secretMapper;

    public Secret querySecret(String id){
        return secretMapper.getOne(id);
    }
    
    public String getSign(Map<String, String> map, String secret) {
    	//拼接参数
		String strTemp = StringUtils.formatParamMap(map) + secret;
		System.out.println("strTemp:"+strTemp);
		
        //加密
        String baseStr = strTemp + TEMPSTR;
        String signTemp = DigestUtils.md5DigestAsHex(baseStr.getBytes()).toUpperCase();
        System.out.println("MD5:"+signTemp);
        
    	return signTemp;
    }

}
