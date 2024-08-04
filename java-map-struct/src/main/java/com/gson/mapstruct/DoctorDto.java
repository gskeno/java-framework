package com.gson.mapstruct;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class DoctorDto {

    private String id;
    private String name;

    private Integer age;

    private BigDecimal money;

    private String salary;

    private Date startTime;

    private LocalDateTime endTime;

    private Date timeStamp;

}
