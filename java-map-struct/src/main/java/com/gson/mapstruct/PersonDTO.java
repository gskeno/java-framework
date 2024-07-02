package com.gson.mapstruct;

import lombok.Data;

import java.util.List;

@Data
public class PersonDTO {
    private String id;
    private String name;
    private List<Person> children;
}
