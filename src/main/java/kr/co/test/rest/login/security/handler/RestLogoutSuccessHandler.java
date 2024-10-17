package kr.co.test.rest.login.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import common.util.ResponseUtil;
import common.util.json.JacksonUtil;
import common.util.map.ResultSetMap;
import kr.co.test.common.Constants;
import kr.co.test.common.ResponseCode;

public class RestLogoutSuccessHandler implements LogoutSuccessHandler {

	@SuppressWarnings("unchecked")
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		ResultSetMap resMap = new ResultSetMap();
		resMap.put(Constants.RESP.RESP_CD, ResponseCode.S0000.getCode());
		resMap.put(Constants.RESP.RESP_MSG, ResponseCode.S0000.getMessage());
		
		String sMessage = JacksonUtil.converterMapToJsonStr(resMap);
		
//		response.setStatus(HttpServletResponse.SC_OK);
		ResponseUtil.setJsonResponse(response, sMessage);
	}
	
}
