package kr.co.test.rest.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import common.spring.resolver.ParamCollector;
import common.util.map.ResultSetMap;
import kr.co.test.common.BaseController;
import kr.co.test.common.Constants;
import kr.co.test.page.manager.service.ManagerService;
import kr.co.test.page.manager.service.ManagerValidtion;

@RestController
@RequestMapping("/api/manager")
public class RestManagerController extends BaseController {

	@Autowired
	private ManagerService managerService;
	
	@RequestMapping("/list")
	@Override
	public ResultSetMap dataSetList(ParamCollector paramCollector) throws Exception {
		ResultSetMap resMap = new ResultSetMap();
		
		// 1. 관리자 조회 유효성 체크
		paramCollector.put("draw", paramCollector.get("page"));
		ResultSetMap validMap = ManagerValidtion.processSrchValidtion(paramCollector);
		if (validMap.isEmpty()) {
			// 1. 관리자 목록 개수 조회
			int nTotCnt = managerService.getManagerCount(paramCollector);
			
			// 2. 관리자 목록 조회
			List<ResultSetMap> list = managerService.getManagerList(paramCollector);
			
			resMap.put("draw", paramCollector.get("page"));
			resMap.putBasic("recordsTotal", nTotCnt);
			resMap.putBasic("recordsFiltered", nTotCnt);
			resMap.put("data", list);
		} else {
			resMap.put(Constants.RESP.RESP_CD, validMap.get(Constants.RESP.RESP_CD));
			resMap.put(Constants.RESP.RESP_MSG, validMap.get(Constants.RESP.RESP_MSG));
		}
		
		return resMap;
	}
	
}
