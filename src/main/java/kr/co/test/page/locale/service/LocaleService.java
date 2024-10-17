package kr.co.test.page.locale.service;

import java.util.List;

import common.spring.resolver.ParamCollector;
import common.util.map.ResultSetMap;

public interface LocaleService {

	public List<ResultSetMap> getLocaleList(ParamCollector paramCollector);
	
	public ResultSetMap getLocale(ParamCollector paramCollector);
	
}
