package com.hjrpc.shirospringboot.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@ConfigurationProperties(prefix="spring.thymeleaf")
public class ThymeleafProperties {
    private static final Charset DEFAULT_ENCODING = StandardCharsets.UTF_8;
    private static final String DEFAULT_PREFIX = "classpath:/templates/";
    private static final String DEFAULT_SUFFIX = ".html";
}
