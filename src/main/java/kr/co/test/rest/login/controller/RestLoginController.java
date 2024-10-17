package kr.co.test.rest.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import common.LogDeclare;
import common.spring.resolver.ParamCollector;
import common.util.map.ResultSetMap;
import kr.co.test.rest.login.service.RestLoginService;

@RestController
@RequestMapping("/api/login")
public class RestLoginController extends LogDeclare {
	
	@Autowired
	private RestLoginService restLoginService;
	
	@PostMapping("/auth")
	public ResultSetMap auth(ParamCollector paramCollector) throws Exception {
		return restLoginService.processAuth(paramCollector);
	}
	
	@Secured ({"ROLE_REFRESH_TOKEN"})
	@RequestMapping("/refresh")
	public ResultSetMap refresh(ParamCollector paramCollector) {
		return restLoginService.processRefresh(paramCollector);
	}
	
}
