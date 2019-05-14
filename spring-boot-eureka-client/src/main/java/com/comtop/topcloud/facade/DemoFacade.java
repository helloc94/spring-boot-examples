package com.comtop.topcloud.facade;

import com.comtop.topcloud.mapper.SecretMapper;
import com.comtop.topcloud.model.Secret;
import com.comtop.topcloud.utils.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.UUID;

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

    /**
     * 查找
     * @param id
     * @return
     */
    public Secret querySecret(String id){
        return secretMapper.getOne(id);
    }

	/**
	 * 查找列表
	 * @return
	 */
	public List<Secret> queryList(){
    	return secretMapper.getAll();
	}
    
    /**
     * 新增
     * @param secret
     */
    public String insert(Secret secret) {
    	secret.setAppKey(UUID.randomUUID().toString().replace("-", "").toLowerCase());
        secret.setAppId(UUID.randomUUID().toString().replace("-", "").toLowerCase());
        secret.setAppSecret(UUID.randomUUID().toString().replace("-", "").toLowerCase());

		System.out.println("-------------------"+secretMapper.insert(secret));
    	return secret.getAppId();
    }
    
    /**
     * 修改
     * @param secret
     */
	public int update(Secret secret) {

		return secretMapper.update(secret);

	}
	
	/**
	 * 删除
	 * @param id
	 */
	public int delete(String id) {

		return secretMapper.delete(id);
	}
	
    /**
     * 获取签名
     * @param map
     * @param secret
     * @return
     */
    public String getSign(Map<String, String> map, String secret) {
    	//拼接参数
		String strTemp = StringUtils.formatParamMap(map) + "secret=" +secret;
		System.out.println("strTemp:"+strTemp);
		
        //加密
        String baseStr = strTemp + TEMPSTR;
        String signTemp = DigestUtils.md5DigestAsHex(baseStr.getBytes()).toUpperCase();
        System.out.println("MD5:"+signTemp);
        
    	return signTemp;
    }

}
