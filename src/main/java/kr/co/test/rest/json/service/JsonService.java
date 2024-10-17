package kr.co.test.rest.json.service;

import common.spring.resolver.ParamCollector;
import common.util.map.ResultSetMap;

public interface JsonService {

	public ResultSetMap data(ParamCollector paramCollector);
	
}
