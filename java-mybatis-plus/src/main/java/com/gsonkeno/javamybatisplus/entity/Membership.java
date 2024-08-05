package com.gsonkeno.javamybatisplus.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@TableName(value = "membership")
@Data
public class Membership implements Serializable {
    private static final long serialVersionUID = 1L;

    private String membershipId;
}
