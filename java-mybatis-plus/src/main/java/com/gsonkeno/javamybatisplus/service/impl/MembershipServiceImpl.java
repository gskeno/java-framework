package com.gsonkeno.javamybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gsonkeno.javamybatisplus.entity.Membership;
import com.gsonkeno.javamybatisplus.mapper.MembershipMapper;
import com.gsonkeno.javamybatisplus.service.MembershipService;
import org.springframework.stereotype.Service;

/**
 * 数据库操作
 */
@Service
public class MembershipServiceImpl extends ServiceImpl<MembershipMapper, Membership> implements MembershipService {

}
