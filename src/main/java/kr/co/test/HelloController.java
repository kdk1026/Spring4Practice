package kr.co.test;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
	
	private static final String MESSAGE = "message";

	@RequestMapping("/hello")
	public String hello(Model model) {
		model.addAttribute(MESSAGE, "JSP");
		return "hello";
	}
	
	@RequestMapping("/hello_vm")
	public String helloVm(Model model) {
		model.addAttribute(MESSAGE, "Velocity");
		return "hello_vm";
	}
	
	@RequestMapping("/hello_ftl")
	public String helloFtl(Model model) {
		model.addAttribute(MESSAGE, "Freemarker");
		return "hello_ftl";
	}
	
	@RequestMapping("/hello_thyme")
	public String helloThyme(Model model) {
		model.addAttribute(MESSAGE, "Thymeleaf");
		return "thymeleaf/hello_thyme";
	}
	
}
