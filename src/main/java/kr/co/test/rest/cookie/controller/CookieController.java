package kr.co.test.rest.cookie.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import common.LogDeclare;
import common.spring.resolver.ParamCollector;
import common.util.sessioncookie.CookieUtilVer2;

@RestController
@RequestMapping("/cookie")
public class CookieController extends LogDeclare {
	
	private static final String TEST_COOKIE_KEY = "hello_cookie";

	@RequestMapping("/set")
	public String set(HttpServletResponse response) {
		CookieUtilVer2.addCookie(response, TEST_COOKIE_KEY, "test", (60*5), false, false, null);
		
		return "Cookie Set Test";
	}
	
	@RequestMapping("/get")
	public String get(ParamCollector paramCollector) {
		String msg = CookieUtilVer2.getCookieValue(paramCollector.getRequest(), TEST_COOKIE_KEY);
		
		return msg;
	}
	
	@RequestMapping("/get_anno")
	public String getAnno(@CookieValue(TEST_COOKIE_KEY) String cookieKey) {
		return cookieKey;
	}
	
	@RequestMapping("/remove")
	public String remove(ParamCollector paramCollector, HttpServletResponse response) {
		CookieUtilVer2.removeCookie(response, TEST_COOKIE_KEY);
		
		return "Cookie Remove Test";
	}
	
}
