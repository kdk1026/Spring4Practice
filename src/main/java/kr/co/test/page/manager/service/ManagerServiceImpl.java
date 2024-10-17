package kr.co.test.page.manager.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import common.LogDeclare;
import common.spring.resolver.ParamCollector;
import common.util.RequestIpUtil;
import common.util.map.ResultSetMap;
import kr.co.test.common.Constants;
import kr.co.test.common.ResponseCode;
import kr.co.test.page.manager.mapper.ManagerMapper;

@Service
public class ManagerServiceImpl extends LogDeclare implements ManagerService {

	@Autowired
	private ManagerMapper managerMapper;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public int getManagerCount(ParamCollector paramCollector) {
		return managerMapper.selectManagerCount(paramCollector.getMapAll());
	}

	@Override
	public List<ResultSetMap> getManagerList(ParamCollector paramCollector) {
		List<ResultSetMap> list = new ArrayList<ResultSetMap>();
		
		list = managerMapper.selectManagerList(paramCollector.getMapAll());
		return list;
	}

	@Override
	public ResultSetMap getCheckUserName(ParamCollector paramCollector) {
		ResultSetMap resMap = new ResultSetMap();

		resMap = managerMapper.selectCheckUserName(paramCollector.getMapAll());

		String sMsg = ResponseCode.S0000.getMessage();
		if (resMap.getString("being").equals("Y")) {
			sMsg = Constants.MESSAGE.ALREADY_USE_USERNAME;
		} else {
			sMsg = Constants.MESSAGE.ALREADY_NOT_USE_USERNAME;
		}
		
		resMap.put(Constants.RESP.RESP_CD, ResponseCode.S0000.getCode());
		resMap.put(Constants.RESP.RESP_MSG, sMsg);
		
		return resMap;
	}

	@Override
	public void addManager(ParamCollector paramCollector) {
		String sIpAddr = RequestIpUtil.getIpAdd(paramCollector.getRequest());
		paramCollector.put("ipAddr", sIpAddr);
		
		String sPwd = paramCollector.getString(Constants.ID_PWD.PASSWORD);
		paramCollector.put(Constants.ID_PWD.PASSWORD, passwordEncoder.encode(sPwd));
		
		managerMapper.insertManagerAccount(paramCollector.getMapAll());
		managerMapper.insertManagerInfo(paramCollector.getMapAll());
	}

	@Override
	public void removeManager(ParamCollector paramCollector) {
		managerMapper.deleteManagerInfo(paramCollector.getMapAll());
		managerMapper.deleteManagerAccount(paramCollector.getMapAll());
	}

	@Override
	public ResultSetMap getManagerInfo(ParamCollector paramCollector) {
		return managerMapper.selectManagerInfo(paramCollector.getMapAll());
	}

	@Override
	public void modifyManager(ParamCollector paramCollector) {
		managerMapper.updateManagerInfo(paramCollector.getMapAll());
		
		String sPwd = paramCollector.getString(Constants.ID_PWD.PASSWORD);
		if ( !StringUtils.isBlank(sPwd) ) {
			paramCollector.put(Constants.ID_PWD.PASSWORD, passwordEncoder.encode(sPwd));
		}
		
		managerMapper.updateManagerAccount(paramCollector.getMapAll());
	}
	
}

