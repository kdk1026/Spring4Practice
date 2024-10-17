package kr.co.test.page.locale.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import common.LogDeclare;
import common.spring.resolver.ParamCollector;
import common.util.map.ParamMap;
import common.util.map.ResultSetMap;
import kr.co.test.common.spring.support.Messages;
import kr.co.test.page.locale.mapper.LocaleMapper;

@Service
public class LocaleServiceImpl extends LogDeclare implements LocaleService, Messages {
	
	@Autowired
	private LocaleMapper localeMapper;

	@Override
	public String getMessage(String code, String locale) {
		ParamMap paramMap = new ParamMap();
		paramMap.put("code", code);
		paramMap.put("lang", locale);
		return localeMapper.selectLocaleMessage(paramMap);
	}

	@Override
	public List<ResultSetMap> getLocaleList(ParamCollector paramCollector) {
		return localeMapper.selectLocaleList(paramCollector.getMapAll());
	}
	
	@Override
	public ResultSetMap getLocale(ParamCollector paramCollector) {
		return localeMapper.selectLocale(paramCollector.getMapAll());
	}
		
}
