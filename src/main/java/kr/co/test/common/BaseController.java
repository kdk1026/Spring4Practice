package kr.co.test.common;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import common.LogDeclare;
import common.spring.resolver.ParamCollector;
import common.util.map.ResultSetMap;

public abstract class BaseController extends LogDeclare {
	
	public String list(ParamCollector paramCollector) throws Exception {
		return null;
	}
	
	public ResultSetMap dataSetList(ParamCollector paramCollector) throws Exception {
		return null;
	}
	
	public String write(ParamCollector paramCollector, Model model) throws Exception {
		return null;
	}
	
	public String writeProc(ParamCollector paramCollector, RedirectAttributes redirectAttributes) throws Exception {
		return null;
	}
	
	public ResultSetMap removeProc(ParamCollector paramCollector) throws Exception {
		return null;
	}
	
	public ResultSetMap removeProc(ParamCollector paramCollector, @RequestParam List<String> items) throws Exception {
		return null;
	}
	
	public String modify(ParamCollector paramCollector, Model model) throws Exception {
		return null;
	}
	
	public String modifyProc(ParamCollector paramCollector, RedirectAttributes redirectAttributes, Model model) throws Exception {
		return null;
	}
	
}
