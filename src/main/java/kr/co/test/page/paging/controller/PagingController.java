package kr.co.test.page.paging.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import common.util.PagingUtil;

@Controller
@RequestMapping("/paging")
public class PagingController {
	
	private static int pagePerRow = 5;
	private static int pagePerScreen = 5;
	private static int totalCnt = 50;

	@RequestMapping("/page/{currentPage}")
	public String page(Model model, @PathVariable String currentPage) {

		PagingUtil paging = new PagingUtil(pagePerRow, pagePerScreen, totalCnt, currentPage, "/paging/page");
		model.addAttribute("paging", paging);

		return "test/paging/paging";
	}
	
}
