package kr.co.test.page.pdf.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.parser.PdfTextExtractor;

import common.LogDeclare;
import common.spring.resolver.ParamCollector;
import kr.co.test.common.spring.util.Spring4FileUtil.FileVO;
import kr.co.test.page.excel.service.ExcelService;
import kr.co.test.page.file.service.FileService;

@Service
public class PdfServiceImpl extends LogDeclare implements PdfService {
	
	@Autowired
	private ExcelService excelService;
	
	@Autowired
	private FileService fileService;

	private Font getKrFont() throws Exception {
		BaseFont baseFont = BaseFont.createFont("c:\\windows\\fonts\\batang.ttc,0"
				, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		return new Font(baseFont);
	}

	@Override
	public Table getPdfContents(ParamCollector paramCollector) throws Exception {
		Font font = this.getKrFont();
		List<Map<String, Object>> list = excelService.getReportList();
		
		Table table = new Table(4);
		table.addCell(new Paragraph("번호", font));
		table.addCell(new Paragraph("제목", font));
		table.addCell(new Paragraph("작성일", font));
		table.addCell(new Paragraph("작성자", font));

		Map<String, Object> hashMap = new HashMap<String, Object>();
		for (int i=1; i<=list.size(); i++) {
			hashMap = list.get(i-1);

			String rowNo = Integer.toString(i);
			table.addCell(rowNo);
			table.addCell(hashMap.get("title").toString());
			table.addCell(hashMap.get("regi_date").toString());
			table.addCell(hashMap.get("writer").toString());
		}
		
		return table;
	}

	@Override
	public String processPdfFileUpload(ParamCollector paramCollector) {
		String sRes = "";
		FileVO fileVO = fileService.processFileUpload(paramCollector);
		
		String destFilePath = fileVO.destFilePath;
		String fileNm = fileVO.saveFileNm;
		
		PdfReader reader;
		
		try {
			reader = new PdfReader(destFilePath + fileNm);
			sRes = new PdfTextExtractor(reader).getTextFromPage(1);
			
			reader.close();
			
		} catch (IOException e) {
			logger.debug("", e);
		}
		
		return sRes;
	}
	
}
