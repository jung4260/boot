package com.coding404.myweb.util.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

//@Component -- 첫번째 방법 autowired로 webconfig에 연결
public class UserAuthHandler implements HandlerInterceptor{
	//로그인여부를 확인하는 인터셉터
	//스프링 설정파일에 인터셉터 등록
	
	//pre - 컨트롤러로 들어가기 전에 실행됨
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		System.out.println("Controller 요청전에 실행됨");
		
		//로그인이 된 사람의 확인
		HttpSession session = request.getSession(); //현재 세션
		String user_id = (String) session.getAttribute("user_id");  //user_id값을 가지고 옴
		
		System.out.println(user_id);
		
		//null일 경우 error(로그인 만료)
		if(user_id != null) { //null이 아닐 경우 로그인이 된 사람
			
			return true; //컨트롤러 실행 시킴
		
		}else { //로그인이 안된 사람
			response.sendRedirect( request.getContextPath() + "/user/login" );
			return false;
		}
		
		//return true; //true - 컨트롤러가 실행됨
		//return false; //false - 컨트롤러가 실행되지 않음
	}

	//post - 컨트롤러에서 dispatcherServlet으로 리턴 전에 실행됨
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	
		System.out.println("Controller 요청 후에 실행됨");
		
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
	
	
	
	
}
