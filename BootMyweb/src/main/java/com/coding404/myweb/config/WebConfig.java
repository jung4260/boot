package com.coding404.myweb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.coding404.myweb.util.interceptor.UserAuthHandler;

@Configuration //스프링 설정파일
public class WebConfig implements WebMvcConfigurer{

	//1st
	//@Autowired
	//private UserAuthHandler userAuthHandler; - UserAuthHandler에서 빈으로 객체를 만들어서 autowired로 연결시키기
	
	//인터셉터 등록
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor( userAuthHandler() )
		        .addPathPatterns("/product/**")
		        .addPathPatterns("/user/**")
		        .excludePathPatterns("/user/login")
		        .excludePathPatterns("/user/loginForm")
		        .excludePathPatterns("/user/join");
		
	}
	
	//2nd
	@Bean
	public UserAuthHandler userAuthHandler() {
		return new UserAuthHandler();
	}
	
	

}
