package kr.co.test.rest.xml.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.LogDeclare;
import common.spring.resolver.ParamCollector;
import kr.co.test.rest.xml.service.XstreamService;
import kr.co.test.rest.xml.vo.XsFood;
import kr.co.test.rest.xml.vo.XsFoods;

@Controller
@RequestMapping("/xstream")
public class XstreamController extends LogDeclare {

	@Autowired
	private XstreamService xstreamService;
	
	@RequestMapping("xml_view")
	public String xmlView(ParamCollector paramCollector, Model model) {
		XsFood food = xstreamService.food(paramCollector);
		
		model.addAttribute("xmlData", food);
		
		return "xstreamView";
	}
	
	@RequestMapping("xmls_view")
	public String xmlsView(ParamCollector paramCollector, Model model) {
		XsFoods foods = xstreamService.foods(paramCollector);
		
		model.addAttribute("xmlData", foods);
		
		return "xstreamView";
	}

	@ResponseBody
	@RequestMapping(value = "xml_converter", produces = MediaType.APPLICATION_XML_VALUE)
	public XsFood xmlConverter(ParamCollector paramCollector) {
		return xstreamService.food(paramCollector);
	}
	
}
