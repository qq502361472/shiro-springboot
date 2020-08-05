package com.hjrpc.shirospringboot.conf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis配置类
 */
@Configuration
@MapperScan("com.hjrpc.shirospringboot.mgb.mapper")
public class MyBatisConfig {
}
