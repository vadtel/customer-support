package org.vadtel.support.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.vadtel.support.dao.config.RepositoryConfiguration;

//@Import(RepositoryConfiguration.class)

@Configuration
@EnableWebMvc
@ComponentScan("org.vadtel.support")
public class ApplicationContextConfig implements WebMvcConfigurer {
}
