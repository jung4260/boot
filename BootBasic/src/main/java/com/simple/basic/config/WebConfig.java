package com.simple.basic.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.simple.basic.controller.HomeController;

//부가적인 bean설정은 이렇게 하면 됩니다.
@Configuration //스프링 설정파일을 의미함
@PropertySource("classpath:/hello.properties") //properties을 스프링의 
public class WebConfig implements WebMvcConfigurer {
	/*
	@Autowired
	ApplicationContext context; //스프링의 빈들을 전역적으로 관리한느 IOC기반의 bean관리 객체

	
	@Value("${server.port}") //properties을 스프링의 properties 파일로 등록되고, 안에 들어있는 키 값들을 참조해서 사용이 가능해짐.
	String port;
	@Value("hello")
	String hello;
	
	
	@Value("${goodbye}")
	String bye;

		
	
	@Bean //스프링이 실행할 때 이 메서드를 실행시켜서 반환되는 값을 bean으로 등록
	void hello() {
		//System.out.println("스프링 bean설정 hello world");
		System.out.println("설정값:" + port);
		System.out.println("설정값:" + hello);
		System.out.println("두번째 프로펄티 파일 설정값:" + bye);
		
		//DI
		HomeController controller = context.getBean(HomeController.class);
		System.out.println(controller);
		
		int count = context.getBeanDefinitionCount();
		String [] arr = context.getBeanDefinitionNames();
		
		System.out.println("빈의 개수:" + count);
		System.out.println(Arrays.toString(arr));
	}
	*/

	
	
}
