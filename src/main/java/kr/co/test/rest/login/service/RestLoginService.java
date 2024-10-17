package kr.co.test.rest.login.service;

import common.spring.resolver.ParamCollector;
import common.util.map.ResultSetMap;

public interface RestLoginService {

	public ResultSetMap processAuth(ParamCollector paramCollector);
	
	public ResultSetMap processRefresh(ParamCollector paramCollector);
}
