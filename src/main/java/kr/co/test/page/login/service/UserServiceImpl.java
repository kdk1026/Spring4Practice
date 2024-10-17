package kr.co.test.page.login.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.co.test.page.login.mapper.UserMapper;
import kr.co.test.page.login.model.AuthenticatedUser;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		System.out.println("=== " + id);
		AuthenticatedUser user = userMapper.selectUser(id);
		user.setAuthorities(this.getAuthorities(id));
		
		return user;
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities(String id) {
		List<String> listStrAuthority = userMapper.selecttAuthorities(id);

		List<GrantedAuthority> listAuthority = new ArrayList<GrantedAuthority>();
		for (String role : listStrAuthority) {
			listAuthority.add(new SimpleGrantedAuthority(role));
		}

		return listAuthority;
	}
	
}
