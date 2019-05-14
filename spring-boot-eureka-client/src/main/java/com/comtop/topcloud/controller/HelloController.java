package com.comtop.topcloud.controller;

import com.comtop.topcloud.facade.DemoFacade;
import com.comtop.topcloud.model.Secret;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/secret")
public class HelloController {

    /**
     * demoFacade
     */
    @Autowired
    private DemoFacade demoFacade;

    @RequestMapping("/hello")
    public String index() {

        return "Hello Spring EurekaClient!";
    }

    /**
     * 校验签名
     * @param app_key
     * @param sign
     * @return
     */
    @PostMapping("/checkSign")
    public Boolean checkSign(
    		@RequestBody Map<String,String> map) {
    	String app_key = map.get("app_key");
    	String sign =  map.get("sign");
    	

        Secret obj = demoFacade.querySecret(app_key);
        
        map.put("sign", "");
        
		String signTemp = demoFacade.getSign(map, obj.getAppSecret());
        
        if(signTemp.equals(sign)) {
        	return true;
        }else {
        	return false;
        }
    }

    /**
     * 查询一条记录
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Secret querySecret(
            @PathVariable String id){
        return demoFacade.querySecret(id);
    }

    /**
     * 查询列表
     * @return
     */
    @GetMapping
    public List<Secret> queryList(){
        return demoFacade.queryList();
    }

    /**
     * 新增记录
     */
    @PostMapping
    public String addSecret(
    		@RequestBody Secret secret) {

        return demoFacade.insert(secret);
    }
    
    /**
     * 修改记录
     */
    @PutMapping
    public int updateSecret(
    		@RequestBody Secret secret){

        return demoFacade.update(secret);
    }

    /**
     * 删除记录
     */
    @DeleteMapping("/{id}")
    public int deleteSecret(
            @PathVariable String id) {

        return demoFacade.delete(id);
    }

}
