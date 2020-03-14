package org.vadtel.support.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebMvc
@ComponentScan("org.vadtel.support.dao.config")
@ComponentScan("org.vadtel.support.service.mapper")
@ComponentScan("org.vadtel.support.service.impl")
@ComponentScan("org.vadtel.support.controller")
public class ApplicationContextConfig implements WebMvcConfigurer {
}
