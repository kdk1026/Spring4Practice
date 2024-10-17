package kr.co.test.page.layout;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import common.LogDeclare;

@Controller
@RequestMapping("/layout")
public class LayoutController extends LogDeclare {

	@RequestMapping("/sitemesh")
	public String sitemesh(Model model) {
		model.addAttribute("message", "Hello");
		return "test/layout/sitemesh";
	}
	
	@RequestMapping("/tiles")
	public String tiles(Model model) {
		model.addAttribute("message", "Hello");
		model.addAttribute("title", "타일즈 테스트");
		return "tiles:/test/layout/tiles";
	}
	
}
