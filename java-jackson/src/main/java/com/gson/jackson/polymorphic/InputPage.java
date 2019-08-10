package com.gson.jackson.polymorphic;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;

@Data
@JsonTypeName(value = "input")
public class InputPage extends Page {
    private String input;

    @Override
    public String toString() {
        return "InputPage{" +
                "input='" + input + '\'' +
                ", uiType='" + uiType + '\'' +
                '}';
    }
}
