package com.gson.jackson.model;

import lombok.Data;

/**
 * 校长
 */
@Data
public class HeadMaster {
    private String name;

    @Override
    public String toString() {
        return "HeadMaster{" +
                "name='" + name + '\'' +
                '}';
    }
}
