package com.coding404.jwt.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.coding404.jwt.command.MemberVO;

//직접 생성할거라서, 빈이 아니여도 됩니다.
public class MyUserDetails implements UserDetails {
	
	//로그인해서 조회한 MemberVO객체
	private MemberVO memberVO;
	
	//반드시 UserVO객체를 멤버변수로 담고 생성
	public MyUserDetails(MemberVO vo) {
		this.memberVO = vo;
	}
		
	//부가적으로 쓰고싶은거 있으면 추가해도됨
	//롤 리턴
	public String getRole() {
		return memberVO.getRole();
	}
	
	//사용자의 권한을 리턴
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		List<GrantedAuthority> list = new ArrayList<>();
		
		//권한이 여러개 라면 반복문 돌리면 됩니다.
		list.add(new GrantedAuthority() {
			@Override
			public String getAuthority() {
				return memberVO.getRole();
			}
		});
		//list.add( () -> memberVO.getRole() );
		
		return list;
	}

	@Override
	public String getPassword() {
		return memberVO.getPassword(); //패스워드를 리턴
	}

	@Override
	public String getUsername() {
		return memberVO.getUsername(); //아이디를 리턴
	}

	@Override
	public boolean isAccountNonExpired() {
		return true; //계정이 만료되지 않았습니까? (true면 네)
	}

	@Override
	public boolean isAccountNonLocked() {
		return true; //계정이 락이 걸리지 않았습니까?
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true; //비밀번호 만료되지 않았습니까?
	}

	@Override
	public boolean isEnabled() {
		return true; //계정 사용 가능? ㅇㅇ
	}

}
