package kr.co.test.page.file.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import common.spring.resolver.ParamCollector;
import kr.co.test.common.spring.util.Spring4FileUtil.FileVO;

public interface FileService {
	
	public List<Map<String, Object>> getListFile(ParamCollector paramCollector);

	public FileVO processFileUpload(ParamCollector paramCollector);
	
	public void processFileDownload(ParamCollector paramCollector, int idx, HttpServletResponse response);
	
}
