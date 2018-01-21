package com.arawn.film.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * web mvc 配置
 * Created by ArawN on 2018/1/1.
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/filmImages/**").addResourceLocations("file:D:\\filmImages\\");
        super.addResourceHandlers(registry);
    }
}
