package kr.co.test.common.spring.view.excel;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import net.sf.jxls.transformer.XLSTransformer;

/**
 * AbstractExcelView deprecated
 */
public class JxlsListView extends AbstractXlsView {

	private static final String TEMPLATE_BASE_PACKAGE = "/WEB-INF/excel";

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			Workbook workbook, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// TemplateFile 가져오기
		String templateFileName = this.getTemplateFile(request, model);

		// SaveFileName 설정
		String destFileName = this.setSaveFileName(request, model);

		// 엑셀에 출력할 내용 가져오기
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> excelContent = (List<Map<String, Object>>) model.get("list");

		// 엑셀 템플릿에 넘겨줄 Bean 설정
		model = new HashMap<String, Object>();
		model.put("list", excelContent);

		// 엑셀 파일 생성
		XLSTransformer transformer = new XLSTransformer();
		//org.apache.poi.ss.usermodel.Workbook
		Workbook resultWorkbook = transformer.transformXLS(new BufferedInputStream(new FileInputStream(templateFileName)), model);

		// 엑셀 다운로드 설정
		response.setHeader("Content-Disposition", "attachment; fileName=\"" + destFileName+ "\"");
		response.setHeader("Content-Transfer-Encoding", "binary");

		// 엑셀 파일 내용 출력
		resultWorkbook.write(response.getOutputStream());
	}

	private String getTemplateFile(HttpServletRequest request, Map<String, Object> model) throws Exception {
		ServletContext context = request.getSession().getServletContext();

		String realPath = context.getRealPath(TEMPLATE_BASE_PACKAGE);
		String templateFileName = realPath + File.separator + model.get("templateFileName").toString();

		return templateFileName;
	}

	private String setSaveFileName(HttpServletRequest request, Map<String, Object> model) throws Exception {
		String fileName = (String) model.get("saveFileName");

		String agent = request.getHeader("User-Agent");
		try {
			if (agent.contains("MSIE") || agent.contains("Trident")) {
				fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", " ");
			} else {
				fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileName;
	}

}
