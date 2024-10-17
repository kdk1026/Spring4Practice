package config.spring.mvc;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import kr.co.test.common.spring.view.FileDownloadView;
import kr.co.test.common.spring.view.JacksonJsonpView;
import kr.co.test.common.spring.view.PdfListView;
import kr.co.test.common.spring.view.excel.ExcelListView;
import kr.co.test.common.spring.view.excel.JxlsListView;

public class ViewConfig {
	
	@Bean
	public MappingJackson2JsonView jsonView() {
		return new MappingJackson2JsonView();
	}
	
	@Bean
	public JacksonJsonpView jsonpView() {
		return new JacksonJsonpView();
	}
	
	@Bean
	public FileDownloadView downloadView() {
		return new FileDownloadView();
	}
	
	@Bean
	public ExcelListView excelListView() {
		return new ExcelListView();
	}
	
	@Bean
	public JxlsListView jxlsListView() {
		return new JxlsListView();
	}
	
	@Bean
	public PdfListView pdfListView() {
		return new PdfListView();
	}
	
}
