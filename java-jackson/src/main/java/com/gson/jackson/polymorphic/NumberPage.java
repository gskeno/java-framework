package com.gson.jackson.polymorphic;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;

@Data
@JsonTypeName(value = "number")
public class NumberPage extends Page{
    private Integer number;

    @Override
    public String toString() {
        return "NumberPage{" +
                "number=" + number +
                ", uiType='" + uiType + '\'' +
                '}';
    }
}
