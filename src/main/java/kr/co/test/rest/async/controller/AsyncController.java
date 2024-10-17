package kr.co.test.rest.async.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import common.LogDeclare;
import kr.co.test.rest.async.service.AsyncService;

@RestController
@RequestMapping("/async")
public class AsyncController extends LogDeclare {

	@Autowired
	private AsyncService asyncService;
	
	@RequestMapping()
	public String test() {
		try {
			asyncService.test1();
			asyncService.test2();
			asyncService.other();
			
		} catch (InterruptedException e) {
			logger.error("", e);
		}
		
		return "Async Test 콘솔 확인";
	}
	
}
