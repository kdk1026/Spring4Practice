package kr.co.test.page.locale.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import common.util.map.ParamMap;
import common.util.map.ResultSetMap;

@Mapper
public interface LocaleMapper {

	public List<ResultSetMap> selectLocaleList(ParamMap paramMap);
	
	public ResultSetMap selectLocale(ParamMap paramMap);
	
	public String selectLocaleMessage(ParamMap paramMap);
}
