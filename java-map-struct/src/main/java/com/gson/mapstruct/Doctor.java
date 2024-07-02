package com.gson.mapstruct;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Doctor {
    private Integer id;
    private String name;

    private String age;

    /**
     * 专业
     */
    private String specialty;

    private String money;

    private BigDecimal salary;


    private String startTime;

    private String endTime;

    private Long timeStamp;



}
