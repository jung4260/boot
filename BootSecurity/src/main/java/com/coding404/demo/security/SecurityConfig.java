package com.coding404.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration //설정파일임
@EnableWebSecurity //이 설정파일을 시큐리티 필터에 등록을 시킴
@EnableGlobalMethodSecurity(prePostEnabled = true) //method에 권한 설정 어노테이션 활성화
public class SecurityConfig {
	
	//로그인 시도 UserDetailService
	@Autowired
	private MyUserDetailsService myUserDetailService;
	
	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		//csrf토큰이 있는데, 사용안함x
		http.csrf().disable();
		
		//권한설정
		//모든요청에 대해서 사용자 인증이 필요합니다.
		//http.authorizeRequests( (authorize) -> authorize.anyRequest().authenticated() );
		
		//특정페이지 인증
		//hello 페이지는 인증이 필요합니다.
		//http.authorizeRequests( (authorize) -> authorize.antMatchers("/hello").authenticated() ); 
		//"user/"밑 모든경로는 인증이 필요합니다.
		//http.authorizeRequests( (a) -> a.antMatchers("/user/**").authenticated());
		
		//user page는 USER권한이 필요함, admin page이는 ADMIN권한이 필요함(인증이 되더라도 권한이 필요함)
//		http.authorizeRequests( (a) -> a.antMatchers("/all").authenticated() //all경로는 인증만 받으면 가능함
//										.antMatchers("/user/**").hasRole("USER") // user는 인증과 user라는 권한이 필요함
//										.antMatchers("/admin/**").hasRole("ADMIN") // admin도 인증과 admin의 권한이 필요함
//										.anyRequest().permitAll()); //모든 요청 허용
		
		//all은 인증필요
		//user는 USER or ADMIN or TESTER중 하나만 가지고 있으면 접근 가능
		//admin은 ADMIN이어야 접근가능
		//나머지 모든 요청은 허용
		//hasAnyRole("USER") "USER"앞에는 ROLE_가 생략되 있다. ROLE_USER를 사용해야한다.
		http.authorizeRequests( (a) -> a.antMatchers("/all").authenticated() 
										.antMatchers("/user/**").hasAnyRole("USER","ADMIN","TESTER")
										.antMatchers("/admin/**").hasRole("ADMIN")
										.anyRequest().permitAll());
		
		//시큐리티 기반의 폼로그인을 사용하겠다.
		http.formLogin()
			.loginPage("/login") //우리가 만들어 놓은 커스터마이징된 페이지의 경로를 로그인 페이지로 사용함
			.loginProcessingUrl("/loginForm") //로그인 시도하는 경로
//			.usernameParameter("username")		   //username파라미터 변경시
//			.passwordParameter("password")	   //password파라미터 변경시
			.defaultSuccessUrl("/hello") // 로그인 성공후 이동할 페이지
			.failureUrl("/login?err=true") //로그인 실패시 이동하고 싶은 경로
			//.failureHandler(authenticationFailureHandler (핸들러) );
			.and()
			.exceptionHandling().accessDeniedPage("/denied") // 권한이 없을시에 돌아가는 페이지
			//.exceptionHandling().accessDeniedHandler(핸들러);
			.and()
			.logout().logoutUrl("/myLogout").logoutSuccessUrl("/hello"); //logout주소로 /myLogout 로그아웃 후에 hello페이지 연결
			
		//Remember Me~~ though i have to say goodbye~~
		http.rememberMe()
			.key("coding404")//rememberMe를 쿠키로 동작시키는데 그때, 쿠키에 저장되는 토큰값을 만들
			.tokenValiditySeconds(3600) //3600 -> 1시간 동안 유효한 토큰
			.rememberMeParameter("remember-me") //화면에서 전달되는 checkbox 파라미터 값
			.userDetailsService(myUserDetailService) //리맴버미 토큰이 있을때 실행시킬 로그인시도 서비스
			.authenticationSuccessHandler(authenticationSuccessHandler()); //인증 성공시 
		
		return http.build();
	}
	
	
	
	
	//인증실패시 핸들러
	@Bean
	public AuthenticationFailureHandler authenticationFailureHandler () {
		
		CustomAuthenticationFailure custom = new CustomAuthenticationFailure();
		custom.setRedirectURL("/login?err=true");
		
		return custom;
	}
	
	
	//리맴버미 핸들러
	@Bean
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
		return new CustomRemeberMeHandler();
	}
	
}