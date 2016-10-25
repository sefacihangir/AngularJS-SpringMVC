package com.artsoft.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.artsoft.*")
public class SpringRESTConfiguration extends WebMvcConfigurerAdapter{
	
	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry){
		registry.addResourceHandler("resources/**").addResourceLocations("/resources/");
	}
	
}
