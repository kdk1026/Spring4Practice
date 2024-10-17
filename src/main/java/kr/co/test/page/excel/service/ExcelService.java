package kr.co.test.page.excel.service;

import java.util.List;
import java.util.Map;

import common.spring.resolver.ParamCollector;

public interface ExcelService {

	public Map<String, Object> getExcelListMap();
	
	public List<Map<String, Object>> getReportList();
	
	public List<Map<String, Object>> processExcelFileUpload(ParamCollector paramCollector);
	
}
