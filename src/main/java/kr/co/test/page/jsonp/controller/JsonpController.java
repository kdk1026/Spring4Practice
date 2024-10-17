package kr.co.test.page.jsonp.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.LogDeclare;
import common.spring.resolver.ParamCollector;
import common.util.json.JacksonUtil;

@Controller
@RequestMapping("/jsonp")
public class JsonpController extends LogDeclare {
	
	@GetMapping("/test")
	public String test(ParamCollector paramCollector, Model model) {
		model.addAttribute("message", "Hello JSONP");
		
		return "test/jsonp/jsonp";
	}
	
	@GetMapping("/test2")
	public String test2(ParamCollector paramCollector, Model model) {
		model.addAttribute("message", "Hello JSONP");
		
		return "test/jsonp/jsonp2";
	}

	@GetMapping("/jsonp_view")
	public String jsonpView(ParamCollector paramCollector, ModelMap model) {
		String sCallback = paramCollector.getRequest().getParameter("stone");
		String sLastName = paramCollector.getRequest().getParameter("lastName");
		String sFirstName = paramCollector.getRequest().getParameter("firstName");
		
		paramCollector.getRequest().setAttribute("myCallback", sCallback);
		model.addAttribute("name", (sLastName + sFirstName));

		return "jsonpView";
	}
	
	@ResponseBody
	@GetMapping("/res")
	public String res(ParamCollector paramCollector, ModelMap model) {
		String sCallback = paramCollector.getRequest().getParameter("stone");
		String sLastName = paramCollector.getRequest().getParameter("lastName");
		String sFirstName = paramCollector.getRequest().getParameter("firstName");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", (sLastName + sFirstName));
		
		String sResult = JacksonUtil.converterMapToJsonStr(map);
		
		return sCallback + "(" + sResult + ")";
	}
	
}
