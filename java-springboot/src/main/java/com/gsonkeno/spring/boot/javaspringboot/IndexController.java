package com.gsonkeno.spring.boot.javaspringboot;

import com.gsonkeno.spring.boot.javaspringboot.config.CustomConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @Autowired
    private CustomConfig customConfig;

    @RequestMapping("/")
    public String index(){
        return customConfig.getCustomName();
    }


    @ResponseBody
    @RequestMapping("/api/queryCardSetting")
    public String queryCardSetting(String templateId){
        System.out.println("templateId=" + templateId);
        return templateId;
    }
}


