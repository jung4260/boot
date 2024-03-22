package com.coding404.demo.security;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.coding404.demo.command.MemberVO;
import com.coding404.demo.member.MemberMapper;

@Service //빈으로 등록되어 있으면, 
public class MyUserDetailsService implements UserDetailsService{

	//loginProcessingUrl에 로그인Url을 등록
	//스프링이 UserDetailsService타입을 찾아서, 사용자가 로그인시 loadUserByUsername 실행시킴
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		System.out.println("사용자가 로그인을 시도함");
		System.out.println("사용자가 입력한 값:" + username);
		
		//로그인 시도 (비밀번호는 시큐리티가 알아서 비교후에 처리)
		//로그인을 해서 VO객체
		
		MemberVO vo = memberMapper.login(username);
		//System.out.println(vo);
		
		//회원정보가 있음 vo가 null이 아니면 회원정보가 있음 -> 비밀번호를 비교를 하기 위해서 UserDetails타입으로 리턴
		if(vo != null) {
			System.out.println(  new MyUserDetails(vo) );
			return new MyUserDetails(vo); //스프링 시큐리티가 비밀번호 비교, 롤 권한, 롤 확인도 해서 로그인 처리 시도
		}
		
		//시큐리티에 설정한 형식대로, 권한까지 처리를 해줍니다.
		//만약 아이디가 없거나, 비밀번호가 틀리면 login?error로 기본이동됩니다.(설정으로 변경가능)
		//시큐리티는 특별한 세션의 모형을 사용함
		//시큐리티세션(new Authentication (new MyUserDetails() ) )모형으로 저장시킵니다.
		
		
		return null;
	}
	
	

}
