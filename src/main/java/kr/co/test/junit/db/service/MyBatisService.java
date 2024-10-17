package kr.co.test.junit.db.service;

import java.util.List;

import common.spring.resolver.ParamCollector;
import common.util.map.ResultSetMap;

public interface MyBatisService {

	public List<ResultSetMap> listUser();
	
	public void addUser(ParamCollector paramCollector);
	
	public ResultSetMap getUser(ParamCollector paramCollector);
	
	public void modifyUser(ParamCollector paramCollector);
	
	public void removeUser(ParamCollector paramCollector);
	
	public int manualTransaction1(ParamCollector paramCollector);
	
	public int manualTransaction2(ParamCollector paramCollector);
	
}
