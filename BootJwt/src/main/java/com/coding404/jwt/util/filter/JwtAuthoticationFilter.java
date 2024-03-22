package com.coding404.jwt.util.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.coding404.jwt.util.JWTService;

public class JwtAuthoticationFilter extends BasicAuthenticationFilter {
	//BasicAuthenticationFilter - HTTP요청의 (BASIC)인증 헤더를 처리하여 결과를 SecurityContextHolder에 저장합니다.
	
	//생성자는 반드시 authenticationManager를 받아줌
	public JwtAuthoticationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("================JWT basic헤더 검사 하는 필터 실행됨==========");
		//헤더에 담긴 토큰을 검사해서 토큰의 무결성 여부를 확인해서 에러를 띄우거나~ 컨트롤러를 실행함~
		
		//1. 헤더값얻기
		String header = request.getHeader("Authorization");
		System.out.println(header);

		
		//토큰을 보내지 않거나, bearer로 시작하지 않으면
		if (header == null || header.startsWith("Bearer ") == false) {
			response.setContentType("text/plain; charset=UTF8");
			response.sendError(403, "토큰이 없습니다");
			return; // 함수 종료 (종료하지 않으면 컨트롤러로 연결이 됩니다)
			
		}
		
		//토큰의 유효성 검사
		
		try {
			String token = header.substring(7);
			boolean result = JWTService.validateToken(token);
			
			if(result) { //정상적인 토큰인 경우
				chain.doFilter(request, response); //컨트롤러 연결
			} else { //토큰의 유효기간이 만료된 경우
				response.setContentType("text/plain; charset=UTF8");
				response.sendError(403, "토큰이 만료되었습니다.");
			}
			
		} catch (Exception e) {
			response.setContentType("text/plain; charset=UTF8");
			response.sendError(403, "잘못된 토큰입니다.");
		}
		
		
		chain.doFilter(request, response); //컨트롤러 연결
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


}
