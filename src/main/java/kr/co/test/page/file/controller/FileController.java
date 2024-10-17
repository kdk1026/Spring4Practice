package kr.co.test.page.file.controller;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import common.LogDeclare;
import common.spring.resolver.ParamCollector;
import common.util.map.ResultSetMap;
import kr.co.test.common.Constants;
import kr.co.test.page.file.service.FileService;
import kr.co.test.page.file.service.FileValidation;

@Controller
@RequestMapping("/file")
public class FileController extends LogDeclare {
	
	@Autowired
	private FileService fileService;
	
	@Value("#{file}")
	private Properties fileProp;
	
	@GetMapping
	public String file(ParamCollector paramCollector, Model model) {
		
		List<Map<String, Object>> list = fileService.getListFile(paramCollector);
		model.addAttribute("list", list);
		
		return "test/file/file";
	}
	
	@PostMapping("/file_upload")
	public String fileUpload(ParamCollector paramCollector, RedirectAttributes redirectAttributes) {
		long nLimitSize = Long.parseLong(fileProp.getProperty("file.limit.size"));
		
		// 1. 파일 업로드 유효성 체크
		ResultSetMap validMap = FileValidation.processValidtion(paramCollector, true, false, nLimitSize);
		
		if ( validMap.isEmpty() ) {
			// 2. 파일 업로드
			fileService.processFileUpload(paramCollector);
		} else {
			redirectAttributes.addFlashAttribute(Constants.RESP.RESP, validMap);
		}
		
		return "redirect:/file";
	}
	
	@RequestMapping("/file_download/{idx}")
	public void fileDownload(ParamCollector paramCollector, HttpServletResponse response
			, @PathVariable int idx) {
		
		fileService.processFileDownload(paramCollector, idx, response);
	}
	
	@RequestMapping("/file_download_view/{idx}")
	public String fileDownloadView(@PathVariable int idx, Model model) {
		model.addAttribute("idx", idx);
		
		return "downloadView";
	}
	
}
