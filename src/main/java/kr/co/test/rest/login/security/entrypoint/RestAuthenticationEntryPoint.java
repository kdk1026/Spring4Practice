package kr.co.test.rest.login.security.entrypoint;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import common.util.ResponseUtil;
import common.util.json.JacksonUtil;
import common.util.map.ResultSetMap;
import kr.co.test.common.Constants;
import kr.co.test.common.ResponseCode;

public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@SuppressWarnings("unchecked")
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		
		ResultSetMap resMap = new ResultSetMap();
		resMap.put(Constants.RESP.RESP_CD, ResponseCode.ACCESS_DENIED.getCode());
		resMap.put(Constants.RESP.RESP_MSG, authException.getMessage());
		
		String sMessage = JacksonUtil.converterMapToJsonStr(resMap);
		
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		ResponseUtil.setJsonResponse(response, sMessage);
	}
	
}
