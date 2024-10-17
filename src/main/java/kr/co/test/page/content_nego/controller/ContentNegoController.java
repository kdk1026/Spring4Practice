package kr.co.test.page.content_nego.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import common.LogDeclare;
import common.spring.resolver.ParamCollector;
import kr.co.test.rest.xml.service.XstreamService;
import kr.co.test.rest.xml.vo.XsFood;

@Controller
@RequestMapping("/content_nego")
public class ContentNegoController extends LogDeclare {

	@Autowired
	private XstreamService xstreamService;
	
	@RequestMapping("food")
	public String food(ParamCollector paramCollector, Model model) {
		
		XsFood food = xstreamService.food(paramCollector);
		model.addAttribute("model", food);
		
		return "test/content_nego/food";
	}
	
}
