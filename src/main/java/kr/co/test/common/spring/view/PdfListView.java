package kr.co.test.common.spring.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;

import common.util.ResponseUtil;

public class PdfListView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document,
			PdfWriter writer, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// PDF에 출력할 내용 설정
		Table table = (Table) model.get("pdfContentsTable");

		// PDF에 출력
		document.add(new Paragraph("AbstractPdfView Test"));
		document.add(table);

		String fileName = (String) model.get("pdfFileName");

		// PDF 이름 및 뷰어로 열기 설정
		ResponseUtil.downloadReportFile(request, response, fileName);
	}

}
