package com.neo.controller;

import com.neo.facade.DemoFacade;
import com.neo.model.Secret;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    /**
     * demoFacade
     */
    @Autowired
    private DemoFacade demoFacade;

    @RequestMapping("/hello")
    public String index() {
    	System.out.println("------------------");
        return "Hello Spring EurekaClient!";
    }
    
    @GetMapping("/checkSign")
    public Boolean checkSign(@RequestParam String app_key,@RequestParam String sign) {


        Secret obj = demoFacade.querySecret(app_key);
        
        Map<String,String> map = new HashMap<String,String>();
		map.put("ZZZ", "zhoujun");
		map.put("CCC", "");
		map.put("aaa", "zhangfei");
		map.put("BBB", "guanyu");
		String signTemp = demoFacade.getSign(map, obj.getAppSecret());
        
        if(signTemp.equals(sign)) {
        	return true;
        }else {
        	return false;
        }
    }
}
