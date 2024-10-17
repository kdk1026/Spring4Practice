package kr.co.test.page.locale.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import common.LogDeclare;
import common.spring.resolver.ParamCollector;
import kr.co.test.common.spring.support.DatabaseMessageSource;

@Controller
@RequestMapping("/locale")
public class LocaleController extends LogDeclare {
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private DatabaseMessageSource databaseMessageSource; 

	@GetMapping
	public String locale(ParamCollector paramCollector, Model model, Locale locale) {
		
		logger.debug("lang is {}", locale.getLanguage());
		
		String msg = messageSource.getMessage("hello", null, locale);
		model.addAttribute("msg", msg);
		
		return "test/locale/locale";
	}
	
	@GetMapping("db")
	public String localeDb(ParamCollector paramCollector, Model model, Locale locale) {
		
		logger.debug("lang is {}", locale.getLanguage());
		
		String msg1 = databaseMessageSource.getMessage("S0000", null, locale);
		model.addAttribute("msg1", msg1);
		
		Object[] args = null;
		String lang = locale.getLanguage();
		if (lang.equals("ko")) {
			args = new Object[] {"제목"};
		}
		else if (lang.equals("en")) {
			args = new Object[] {"title"};
		}
		else if (lang.equals("ja")) {
			args = new Object[] {"タイトル"};
		}
		
		String msg2 = databaseMessageSource.getMessage("9999", args, locale);
		model.addAttribute("msg2", msg2);
		
		return "test/locale/locale_db";
	}
	
}
