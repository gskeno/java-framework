package com.gson.spring;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class CustomPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

    public Map<String,String> map;

    public void init(){
        map = new HashMap<>();
        map.put("height","90");
        map.put("diameter","45");
    }

    @Override
    protected String resolvePlaceholder(String placeholder, Properties props) {
        if (map != null && map.containsKey(placeholder)){
            return map.get(placeholder);
        }
        return super.resolvePlaceholder(placeholder, props);
    }
}
