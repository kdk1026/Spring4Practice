package kr.co.test.page.excel.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import common.LogDeclare;
import common.spring.resolver.ParamCollector;
import common.util.excel.PoiUtil;

@Service
public class ExcelServiceImpl extends LogDeclare implements ExcelService {
	
	@Value("#{file}")
	private Properties fileProp;

	@Override
	public Map<String, Object> getExcelListMap() {
		Map<String, Object> resMap = null;

		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = null;

		map = new HashMap<String, Object>();
		map.put("title", "Test1");
		map.put("regi_date", "13-01-01");
		map.put("writer", "aaa");
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("title", "Test2");
		map.put("regi_date", "13-02-01");
		map.put("writer", "bbb");
		list.add(map);

		String[] headerNames = {"번호", "제목", "등록일", "등록자"};
		String[] sortedKeys = {"title", "regi_date", "writer"};

		resMap = new HashMap<String, Object>();
		resMap.put("list", list);
		resMap.put("headerNames", headerNames);
		resMap.put("sortedKeys", sortedKeys);

		return resMap;
	}

	@Override
	public List<Map<String, Object>> getReportList() {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = null;

		map = new HashMap<String, Object>();
		map.put("title", "Test1");
		map.put("regi_date", "13-01-01");
		map.put("writer", "aaa");
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("title", "Test2");
		map.put("regi_date", "13-02-01");
		map.put("writer", "bbb");
		list.add(map);

		return list;
	}

	@Override
	public List<Map<String, Object>> processExcelFileUpload(ParamCollector paramCollector) {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		
		MultipartFile multipartFile = paramCollector.getMultipartFile("file");
		
		try {
			list = PoiUtil.readExcel(multipartFile.getInputStream(), multipartFile.getOriginalFilename());
			
		} catch (IOException e) {
			logger.error("", e);
		}
		
		return list;
	}
	
}
