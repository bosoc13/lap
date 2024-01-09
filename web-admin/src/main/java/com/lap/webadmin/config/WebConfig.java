package com.lap.webadmin.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan(basePackages = "com.lap.common.entity")
public class WebConfig {

}
