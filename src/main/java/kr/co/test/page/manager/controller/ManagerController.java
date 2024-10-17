package kr.co.test.page.manager.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import common.annotation.RsaDecrypt;
import common.annotation.RsaKeyGenerator;
import common.spring.resolver.ParamCollector;
import common.util.json.GsonUtil;
import common.util.map.ResultSetMap;
import kr.co.test.common.BaseController;
import kr.co.test.common.Constants;
import kr.co.test.common.ResponseCode;
import kr.co.test.page.manager.service.ManagerService;
import kr.co.test.page.manager.service.ManagerValidtion;

/**
 * 관리자 관리
 */
@Controller
@RequestMapping("/admin/manager")
public class ManagerController extends BaseController {
	
	@Autowired
	private ManagerService managerService;
	
	/**
	 * 목록 화면
	 * @param paramCollector
	 * @return
	 * @throws Exception
	 */
	@GetMapping
	@Override
	public String list(ParamCollector paramCollector) throws Exception {
		return "admin/manager/manager";
	}
	
	/**
	 * 목록 데이터 조회
	 * @param paramCollector
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@PostMapping("/dataSetList")
	@Override
	public ResultSetMap dataSetList(ParamCollector paramCollector) throws Exception {
		ResultSetMap resMap = new ResultSetMap();
		
		// 1. 관리자 목록 개수 조회
		int nTotCnt = managerService.getManagerCount(paramCollector);
		
		// 2. 관리자 목록 조회
		List<ResultSetMap> list = managerService.getManagerList(paramCollector);
		
		resMap.put("draw", paramCollector.get("draw"));
		resMap.putBasic("recordsTotal", nTotCnt);
		resMap.putBasic("recordsFiltered", nTotCnt);
		resMap.put("data", list);
		
		return resMap;
	}

	/**
	 * 등록 화면
	 * @param paramCollector
	 * @return
	 * @throws Exception
	 */
	@RsaKeyGenerator
	@GetMapping("/write")
	@Override
	public String write(ParamCollector paramCollector, Model model) throws Exception {
		@SuppressWarnings("unchecked")
		Map<String, Object> rsaPublicMap = (Map<String, Object>) paramCollector.get(Constants.RSA_PUBLIC);
		model.addAllAttributes(rsaPublicMap);
		
		return "admin/manager/managerWriter";
	}
	
	/**
	 * 등록 처리
	 * @param paramCollector
	 * @return
	 * @throws Exception
	 */
	@RsaDecrypt
	@PostMapping("/writeProc")
	@Override
	public String writeProc(ParamCollector paramCollector, RedirectAttributes redirectAttributes) throws Exception {
		ResultSetMap resMap = new ResultSetMap();
		String sUri = "";
		
		try {
			// 1. 관리자 관리 유효성 체크
			ResultSetMap validMap = ManagerValidtion.processValidtion(paramCollector, Constants.UTF8, false, false);
			if (validMap.isEmpty()) {
				// 2. 관리자 등록
				managerService.addManager(paramCollector);
				
				resMap.put(Constants.RESP.RESP_CD, ResponseCode.S0000.getCode());
				resMap.put(Constants.RESP.RESP_MSG, ResponseCode.S0000.getMessage());
				sUri = "redirect:/admin/manager";
				
			} else {
				resMap.put(Constants.RESP.RESP_CD, validMap.get(Constants.RESP.RESP_CD));
				resMap.put(Constants.RESP.RESP_MSG, validMap.get(Constants.RESP.RESP_MSG));
				sUri = "redirect:/admin/manager/writer";				
			}
			
		} catch (Exception e) {
			logger.error("", e);
			String sMsg = ResponseCode.F0003.getMessage() + Constants.MESSAGE.SAVE_ERROR_PREFIX;
			
			resMap.put(Constants.RESP.RESP_CD, ResponseCode.F0003.getCode());
			resMap.put(Constants.RESP.RESP_MSG, sMsg);
			sUri = "redirect:/admin/manager/writer";
		}
		
		@SuppressWarnings("unchecked")
		String sJson = GsonUtil.converterMapToJsonStr(resMap);
		redirectAttributes.addFlashAttribute("vo", sJson);
		
		return sUri;
	}
	
	/**
	 * 아이디 유무 조회
	 * @param paramCollector
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/checkUserName")
	public ResultSetMap checkUserName(ParamCollector paramCollector) throws Exception {
		ResultSetMap resMap = new ResultSetMap();
		
		// 1. 관리자 관리 유효성 체크
		ResultSetMap validMap = ManagerValidtion.processValidtion(paramCollector, Constants.UTF8, false, true);
		if (validMap.isEmpty()) {
			// 2. 관리자 아이디 유무 조회
			resMap = managerService.getCheckUserName(paramCollector);
		} else {
			resMap.put(Constants.RESP.RESP_CD, validMap.get(Constants.RESP.RESP_CD));
			resMap.put(Constants.RESP.RESP_MSG, validMap.get(Constants.RESP.RESP_MSG));
		}
		return resMap;
	}
	
	/**
	 * 삭제 처리
	 * @param paramCollector
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@PostMapping("/removeProc")
	@Override
	public ResultSetMap removeProc(ParamCollector paramCollector, @RequestParam List<String> items) throws Exception {
		ResultSetMap resMap = new ResultSetMap();

		// 1. 삭제할 항목 유무 확인
		if (items.isEmpty()) {
			String sMsg = "삭제할 항목이 없습니다.<br/>처음부터 다시 시도해주세요.";
			
			resMap.put(Constants.RESP.RESP_CD, ResponseCode.F0001.getCode());
			resMap.put(Constants.RESP.RESP_MSG, ResponseCode.F0001.getMessage(sMsg));
			
			return resMap;
		}
		
		try {
			// 2. 관리자 삭제
			paramCollector.put("usernames", items);
			managerService.removeManager(paramCollector);
			
			resMap.put(Constants.RESP.RESP_CD, ResponseCode.S0000.getCode());
			resMap.put(Constants.RESP.RESP_MSG, ResponseCode.S0000.getMessage());
			
		} catch (Exception e) {
			logger.error("", e);
			resMap.put(Constants.RESP.RESP_CD, ResponseCode.F0003.getCode());
			resMap.put(Constants.RESP.RESP_MSG, ResponseCode.F0003.getMessage() + Constants.MESSAGE.SAVE_ERROR_PREFIX);
		}
		
		return resMap;
	}
	
	/**
	 * 상세/수정 화면
	 * @param paramCollector
	 * @return
	 * @throws Exception
	 */
	@RsaKeyGenerator
	@GetMapping("/modify/{username}")
	public String modify(ParamCollector paramCollector, Model model, @PathVariable String username) throws Exception {
		@SuppressWarnings("unchecked")
		Map<String, Object> rsaPublicMap = (Map<String, Object>) paramCollector.get(Constants.RSA_PUBLIC);
		model.addAllAttributes(rsaPublicMap);
		
		// 1. 관리자 상세 조회
		paramCollector.put(Constants.ID_PWD.USERNAME, username);
		ResultSetMap infoMap = managerService.getManagerInfo(paramCollector);
		model.addAttribute("info", infoMap);
		
		return "admin/manager/managerModify";
	}
	
	/**
	 * 수정 처리
	 * @param paramCollector
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 */
	@RsaDecrypt
	@PostMapping("/modifyProc")
	@Override
	public String modifyProc(ParamCollector paramCollector, RedirectAttributes redirectAttributes, Model model) throws Exception {
		ResultSetMap resMap = new ResultSetMap();
		String sUri = "";
		String sUsername = paramCollector.getString(Constants.ID_PWD.USERNAME);
		
		try {
			// 1. 관리자 관리 유효성 체크
			ResultSetMap validMap = ManagerValidtion.processValidtion(paramCollector, Constants.UTF8, true, false);
			if (validMap.isEmpty()) {
				// 2. 관리자 수정
				managerService.modifyManager(paramCollector);
				
				resMap.put(Constants.RESP.RESP_CD, ResponseCode.S0000.getCode());
				resMap.put(Constants.RESP.RESP_MSG, ResponseCode.S0000.getMessage());
				sUri = "redirect:/admin/manager";
			} else {
				resMap.put(Constants.RESP.RESP_CD, validMap.get(Constants.RESP.RESP_CD));
				resMap.put(Constants.RESP.RESP_MSG, validMap.get(Constants.RESP.RESP_MSG));
				sUri = "redirect:/admin/manager/modify/"+sUsername;	
			}
			
		} catch (Exception e) {
			logger.error("", e);
			resMap.put(Constants.RESP.RESP_CD, ResponseCode.F0003.getCode());
			resMap.put(Constants.RESP.RESP_MSG, ResponseCode.F0003.getMessage() + Constants.MESSAGE.SAVE_ERROR_PREFIX);
			
			sUri = "redirect:/admin/manager/modify/"+sUsername;
		}
		
		@SuppressWarnings("unchecked")
		String sJson = GsonUtil.converterMapToJsonStr(resMap);
		redirectAttributes.addFlashAttribute("vo", sJson);
		
		return sUri;
	}
	
}
