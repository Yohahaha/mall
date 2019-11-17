package com.yoha.mall.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.yoha.mall.mbg.mapper", "com.yoha.mall.dao"})
public class MybatisConfig {
}
