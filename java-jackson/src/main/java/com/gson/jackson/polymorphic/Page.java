package com.gson.jackson.polymorphic;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

/**
 * 注解里的visible字段：如果为false，那么反序列化时，
 * 类型id字段（在这个demo里是type字段）的值将不会被反序列化到POJO中。
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", visible = true)
@Data
public class Page {
    private String type;
    private String name;
    protected String uiType;
    private String label;
}
