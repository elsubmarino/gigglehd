package com.gigglehd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.gigglehd.interceptors.LoginInterceptor;
import com.gigglehd.interceptors.LoginMaintainInterceptor;


@EnableWebMvc
@Configuration
@ComponentScan({ "com.gigglehd" })
class WebMvcConfig extends WebMvcConfigurerAdapter {
	@Autowired
	LoginInterceptor handler;

	@Autowired
	LoginMaintainInterceptor handler2;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(handler).addPathPatterns("/board/write");
		registry.addInterceptor(handler).addPathPatterns("/board/modify");
		registry.addInterceptor(handler).addPathPatterns("/board/writeComments");
		registry.addInterceptor(handler).addPathPatterns("/board/delete");
		registry.addInterceptor(handler).addPathPatterns("/board/upload");
		registry.addInterceptor(handler2).addPathPatterns("/**");
	}

}