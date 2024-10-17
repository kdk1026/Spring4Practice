package kr.co.test.page.login.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

	Collection<GrantedAuthority> getAuthorities(String id);
	
}
