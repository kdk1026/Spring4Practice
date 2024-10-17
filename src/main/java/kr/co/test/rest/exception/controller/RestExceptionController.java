package kr.co.test.rest.exception.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import common.LogDeclare;
import kr.co.test.common.exception.RestException;
import kr.co.test.common.exception.RestRuntimeException;

@Controller
@RequestMapping("/rest_exception")
public class RestExceptionController extends LogDeclare {

	@RequestMapping("/test1")
	public String handleRequest1() throws Exception {
		String msg = String.format("Test exception from class: %s",
				this.getClass().getSimpleName());
		
		throw new RestException(msg);
	}
	
	@RequestMapping("/test2")
	public String handleRequest2() throws Exception {
		String msg = String.format("Test exception from class: %s",
				this.getClass().getSimpleName());
		
		throw new RestRuntimeException(msg);
	}
	
}
