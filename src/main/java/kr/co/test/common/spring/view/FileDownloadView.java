package kr.co.test.common.spring.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.AbstractView;

import common.spring.resolver.ParamCollector;
import kr.co.test.page.file.service.FileService;

public class FileDownloadView extends AbstractView {

	@Autowired
	private FileService fileService;
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		int idx = (Integer) model.get("idx");
		
		ParamCollector paramCollector = new ParamCollector();
		paramCollector.setRequest(request);
		
		fileService.processFileDownload(paramCollector, idx, response);
	}
	
}
