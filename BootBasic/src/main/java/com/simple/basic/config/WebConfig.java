package com.simple.basic.config;

import java.util.Arrays;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.mysql.cj.xdevapi.SessionFactory;
import com.simple.basic.controller.HomeController;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

//부가적인 bean설정은 이렇게 하면 됩니다.
//@Configuration //스프링 설정파일을 의미함
//@PropertySource("classpath:/application.properties") //
public class WebConfig implements WebMvcConfigurer {
	
//	@Autowired
//	ApplicationContext applicationContext;
//	
//	//1. 히카리 config객체
//	//spring.datasource.hikari로 시작하는 설정 정보를 읽어와서 메서드에 적용을 시킴
//	@Bean
//	@ConfigurationProperties(prefix = "spring.datasource.hikari")
//	public HikariConfig config() {
//		return new HikariConfig();
//	}
//	
//	//2. 데이터소스객체 생성 <- config 의존 주입
//	@Bean
//	public DataSource dataSource() {
//		return new HikariDataSource(config());
//	}
//	
//	//3 세션팩토리 빈 설정 (인터페이스의 xml 구현체를 만들고 관리하는 빈)
//	//dataSource객체가 있다면 자동으로 넣어줍니다.
//	@Bean
//	public SqlSessionFactory sqlSessionFactory(DataSource dataSource ) throws Exception{
//		
//		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
//		bean.setDataSource(dataSource);
//		//부가적인 설정들 (커스터마이징 경로)
//		bean.setTypeAliasesPackage("com.simple.basic.command");
//		bean.setMapperLocations(applicationContext.getResources("classpath:/mapper/**/*.xml"));
//		return bean.getObject();
//	}
//	
//	@Bean
//	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
//		return new SqlSessionTemplate(sqlSessionFactory);
//	}
//	
//	
//	
	
	
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

