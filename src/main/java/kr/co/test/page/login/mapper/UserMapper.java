package kr.co.test.page.login.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import common.util.map.ParamMap;
import kr.co.test.page.login.model.AuthenticatedUser;

@Mapper
public interface UserMapper {

	public AuthenticatedUser selectUser(String id);
	
	public List<String> selecttAuthorities(String id);
	
	public void updateLastLoginDt(ParamMap paramMap);
}
