package kr.co.test.page.exception.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import common.LogDeclare;

@Controller
@RequestMapping("/page_exception")
public class PageExceptionController extends LogDeclare {

	@GetMapping("/test1")
	public String handleRequest1() throws Exception {
		String msg = String.format("Test exception from class: %s",
                this.getClass().getSimpleName());

		throw new Exception(msg);
	}
	
	@GetMapping("/test2")
	public String handleRequest2() throws Exception {
		String msg = String.format("Test exception from class: %s",
				this.getClass().getSimpleName());
		
		throw new RuntimeException(msg);
	}
	
}
