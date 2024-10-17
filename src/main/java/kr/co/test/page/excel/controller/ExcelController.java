package kr.co.test.page.excel.controller;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import common.LogDeclare;
import common.spring.resolver.ParamCollector;
import common.util.map.ResultSetMap;
import kr.co.test.common.Constants;
import kr.co.test.page.excel.service.ExcelService;
import kr.co.test.page.file.service.FileValidation;

@Controller
@RequestMapping("/excel")
public class ExcelController extends LogDeclare {
	
	@Autowired
	private ExcelService excelService;
	
	@Value("#{file}")
	private Properties fileProp;
	
	@GetMapping
	public String excel(ParamCollector paramCollector, Model model) {
		return "test/excel/excel";
	}
	
	@RequestMapping(value="/excel_down")
	public String excel_down(Model model) {
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> list = (List<Map<String, Object>>) excelService.getExcelListMap().get("list");

		String[] headerNames = (String[]) excelService.getExcelListMap().get("headerNames");
		String[] sortedKeys = (String[]) excelService.getExcelListMap().get("sortedKeys");
		String fileName = "POI 리스트.xls";

		model.addAttribute("list", list);
		model.addAttribute("headerNames", headerNames);
		model.addAttribute("sortedKeys", sortedKeys);
		model.addAttribute("excelFileName", fileName);

		return "excelListView";
	}

	@RequestMapping(value="/jxls_down")
	public String jxls_down(Model model) {
		List<Map<String, Object>> list = excelService.getReportList();

		String fileName = "Jxls 리스트.xls";
		String templateFileName = "excel_template.xls";

		model.addAttribute("list", list);
		model.addAttribute("templateFileName", templateFileName);
		model.addAttribute("saveFileName", fileName);

		return "jxlsListView";
	}
	
	@PostMapping("/excel_upload")
	public String excelUpload(ParamCollector paramCollector, RedirectAttributes redirectAttributes) {
		long nLimitSize = Long.parseLong(fileProp.getProperty("file.limit.size"));
		
		// 1. 파일 업로드 유효성 체크
		ResultSetMap validMap = FileValidation.processValidtion(paramCollector, true, false, nLimitSize);
		
		if ( validMap.isEmpty() ) {
			// 2. 파일 업로드
			List<Map<String, Object>> list = excelService.processExcelFileUpload(paramCollector);
			redirectAttributes.addFlashAttribute("list", list);
			
		} else {
			redirectAttributes.addFlashAttribute(Constants.RESP.RESP, validMap);
		}
		
		return "redirect:/excel";
	}

}
