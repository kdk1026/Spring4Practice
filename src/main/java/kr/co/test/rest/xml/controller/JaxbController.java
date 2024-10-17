package kr.co.test.rest.xml.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.LogDeclare;
import common.spring.resolver.ParamCollector;
import kr.co.test.rest.xml.service.JaxbService;
import kr.co.test.rest.xml.vo.JaxFood;
import kr.co.test.rest.xml.vo.JaxFoods;

@Controller
@RequestMapping("/jaxb")
public class JaxbController extends LogDeclare {

	@Autowired
	private JaxbService jaxbService;
	
	@RequestMapping("xml_view")
	public String xmlView(ParamCollector paramCollector, Model model) {
		JaxFood food = jaxbService.food(paramCollector);
		
		model.addAttribute("xmlData", food);
		
		return "jaxbView";
	}
	
	@RequestMapping("xmls_view")
	public String xmlsView(ParamCollector paramCollector, Model model) {
		JaxFoods foods = jaxbService.foods(paramCollector);
		
		model.addAttribute("xmlData", foods);
		
		return "jaxbView";
	}
	
	@ResponseBody
	@RequestMapping(value = "xml_converter", produces = MediaType.APPLICATION_XML_VALUE)
	public JaxFood xmlConverter(ParamCollector paramCollector) {
		return jaxbService.food(paramCollector);
	}
	
}
