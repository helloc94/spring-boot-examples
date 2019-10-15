package com.neo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.neo.utils.HttpClientUtils;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String index() {
    	System.out.println("------------------");
		try {
			List<JSONObject > objs = JSON.parseArray(HttpClientUtils.doGet("http://10.10.4.83/api/projects?public=1").getContent(), JSONObject .class);
			for(JSONObject  obj: objs) {
				System.out.println("#" + obj.get("name"));

				String project = obj.get("project_id").toString();
				List<JSONObject > repos = JSON.parseArray(HttpClientUtils.doGet("http://10.10.4.83/api/repositories?project_id="+project).getContent(), JSONObject .class);
				for(JSONObject  repo: repos) {

					String name = repo.get("name").toString();
					List<JSONObject > tags = JSON.parseArray(HttpClientUtils.doGet("http://10.10.4.83/api/repositories/"+name+"/tags").getContent(), JSONObject .class);
					for(JSONObject  tag: tags) {
						String imageurl = "harbor.cloudadmin.cloud.csg.cn/" + name +":"+ tag.get("name");
						String pull = "docker pull " + imageurl;
						System.out.println(pull);

						String filename = name + "-" + tag.get("name") + ".tar";
						String save = "docker save -o " + filename + " " + imageurl;
						System.out.println(save);

						System.out.println();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        return "Hello Spring Boot 2.0!";
    }
}