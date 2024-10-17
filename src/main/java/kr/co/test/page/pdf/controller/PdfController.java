package kr.co.test.page.pdf.controller;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lowagie.text.Table;

import common.LogDeclare;
import common.spring.resolver.ParamCollector;
import common.util.map.ResultSetMap;
import kr.co.test.common.Constants;
import kr.co.test.page.file.service.FileValidation;
import kr.co.test.page.pdf.service.PdfService;

@Controller
@RequestMapping("/pdf")
public class PdfController extends LogDeclare {

	@Autowired
	private PdfService pdfService;
	
	@Value("#{file}")
	private Properties fileProp;
	
	@RequestMapping(method = RequestMethod.GET)
	public String pdf(ParamCollector paramCollector, Model model) {
		return "test/pdf/pdf";
	}
	
	@RequestMapping(value="/pdf_down")
	public String pdfDown(ParamCollector paramCollector, Model model) throws Exception {
		Table pdfContentsTable = pdfService.getPdfContents(paramCollector);
		String fileName = "PDF 리스트.pdf";
		
        model.addAttribute("pdfContentsTable", pdfContentsTable);
        model.addAttribute("pdfFileName", fileName);
		
		return "pdfListView";
	}
	
	@PostMapping("/pdf_upload")
	public String pdfUpload(ParamCollector paramCollector, RedirectAttributes redirectAttributes) {
		long nLimitSize = Long.parseLong(fileProp.getProperty("file.limit.size"));
		
		// 1. 파일 업로드 유효성 체크
		ResultSetMap validMap = FileValidation.processValidtion(paramCollector, true, false, nLimitSize);
		
		if ( validMap.isEmpty() ) {
			// 2. 파일 업로드
			String sRes = pdfService.processPdfFileUpload(paramCollector);
			redirectAttributes.addFlashAttribute("item", sRes);
			
		} else {
			redirectAttributes.addFlashAttribute(Constants.RESP.RESP, validMap);
		}
		
		return "redirect:/pdf";
	}
	
}
