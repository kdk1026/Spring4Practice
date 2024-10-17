package kr.co.test.page.pdf.service;

import com.lowagie.text.Table;

import common.spring.resolver.ParamCollector;

public interface PdfService {
	
	public Table getPdfContents(ParamCollector paramCollector) throws Exception;
	
	public String processPdfFileUpload(ParamCollector paramCollector);
	
}
