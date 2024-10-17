package kr.co.test.rest.xml.service;

import common.spring.resolver.ParamCollector;
import kr.co.test.rest.xml.vo.JaxFood;
import kr.co.test.rest.xml.vo.JaxFoods;

public interface JaxbService {

	public JaxFood food(ParamCollector paramCollector);
	
	public JaxFoods foods(ParamCollector paramCollector);
}
