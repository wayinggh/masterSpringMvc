package com.github.spring.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@EnableConfigurationProperties(PictureUploadProperties.class)
@SpringBootApplication
public class MasterSpringMvcApplicationConfigurer extends WebMvcConfigurerAdapter {
}
