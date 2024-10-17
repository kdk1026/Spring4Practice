package kr.co.test.rest.xml.service;

import common.spring.resolver.ParamCollector;
import kr.co.test.rest.xml.vo.XsFood;
import kr.co.test.rest.xml.vo.XsFoods;

public interface XstreamService {

	public XsFood food(ParamCollector paramCollector);
	
	public XsFoods foods(ParamCollector paramCollector);
	
}
