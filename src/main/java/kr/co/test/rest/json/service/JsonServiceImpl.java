package kr.co.test.rest.json.service;

import org.springframework.stereotype.Service;

import common.LogDeclare;
import common.spring.resolver.ParamCollector;
import common.util.map.ResultSetMap;

@Service
public class JsonServiceImpl extends LogDeclare implements JsonService {

	@Override
	public ResultSetMap data(ParamCollector paramCollector) {
		
		ResultSetMap resMap = new ResultSetMap();
		resMap.put("food", "사과");
		resMap.put("product", "tv");
		
		return resMap;
	}
	
}
