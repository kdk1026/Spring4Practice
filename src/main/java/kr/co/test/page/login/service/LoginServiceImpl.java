package kr.co.test.page.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import common.spring.resolver.ParamCollector;
import kr.co.test.page.login.mapper.UserMapper;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public void updateLastLoginDt(ParamCollector paramCollector) {
		userMapper.updateLastLoginDt(paramCollector.getMapAll());
	}
	
}
