package com.coding404.jwt.security;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.coding404.jwt.util.filter.CustomLoginFilter;
import com.coding404.jwt.util.filter.FilterOne;
import com.coding404.jwt.util.filter.FilterTwo;
import com.coding404.jwt.util.filter.JwtAuthoticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		//1. 기본설정
		http.csrf().disable(); 
		http.formLogin().disable(); //form로그인 기반을 폐기
		http.httpBasic().disable(); //http의 기본 인증형태도 폐기 Authorization에는 원래 (아이디, 비밀번호) 형태인데, 이것을 폐기
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //세션기반의 인증을 사용하지 않고 추후 토큰방식을 사용함
		
		//2. 크로스 오리진 필터 활성화
		http.cors( Customizer.withDefaults()  );
		
		//3. 시큐리티에 필터추가.
		//http.addFilter( new FilterOne() ); //addFilter에는 시큐리티 타입의 필터만 등록이 되요.
		//시큐리티 타입의 필터가 아니라, 일반 필터라면, 시큐리티의 필터 전후로 필터를 추가.
		
		//사용자 정의 필터를 추가하고 싶으면 이렇게 하면 됩니다.~
//		http.addFilterBefore(new FilterOne(), UsernamePasswordAuthenticationFilter.class ); //시큐리티 타입의 필터보다 이전에 커스터마이징한 필터를 등록
//		http.addFilterAfter(new FilterTwo(), FilterOne.class );
		
		
		//7. authenticationManager얻기
		//8. 패스워드 인코더 추가
		AuthenticationManager authenticationManager = 
				authenticationManager(http.getSharedObject(AuthenticationConfiguration.class));		
			
		
		//스프링 시큐리티 타입의 필터를 커스터마이징 해서 사용. (로그인)
		//클라이언트에서 /login post방식으로 요청
//		http.addFilter( new CustomLoginFilter(authenticationManager) ); //로그인필터
//		http.addFilter( new JwtAuthoticationFilter(authenticationManager)); //JWT인증필터
		
		http.requestMatchers().antMatchers("login/")
							  .and()
							  .addFilter( new CustomLoginFilter(authenticationManager) );
		
		http.requestMatchers().antMatchers("api/v1/**")
						   	  .and()
						   	  .addFilter(new JwtAuthoticationFilter(authenticationManager));
		
		return http.build();
	}
	
	
	//크로스 오리진 필터 - 서버가 다를때, 옵션요청을 보내게 되는데, 옵션요청에 요청을 허용할 도메인 주소를 헤더에 담아 보내는작업.
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("*"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		
		return source;
	}
	

	//8. authenticationManager객체
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	
	
	
	
	
	
}
