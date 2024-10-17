package kr.co.test.rest.json.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.LogDeclare;
import common.spring.resolver.ParamCollector;
import common.util.map.ResultSetMap;
import kr.co.test.rest.json.service.JsonService;
import kr.co.test.rest.xml.service.XstreamService;
import kr.co.test.rest.xml.vo.XsFood;

@Controller
@RequestMapping("/json")
public class JsonController extends LogDeclare {

	@Autowired
	private JsonService jsonService;
	
	@Autowired
	private XstreamService xstreamService;
	
	/**
	 * <pre>
	 * message-converter 사용 안할 시, produces 불필요
	 *  - @ResponseBody 기본이 json 
	 * </pre>
	 * @param paramCollector
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "json_converter", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultSetMap jsonConverter(ParamCollector paramCollector) {
		return jsonService.data(paramCollector);
	}
	
	@ResponseBody
	@RequestMapping(value = "json_converter2", produces = MediaType.APPLICATION_JSON_VALUE)
	public XsFood jsonConverter2(ParamCollector paramCollector) {
		return xstreamService.food(paramCollector);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("json_view")
	public String jsonView(ParamCollector paramCollector, Model model) {
		ResultSetMap resMap = jsonService.data(paramCollector);
		
		model.addAllAttributes(resMap);
		
		return "jsonView";
	}
	
}
