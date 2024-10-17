package kr.co.test.rest.login.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import common.util.json.JacksonUtil;
import common.util.map.ResultSetMap;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import kr.co.test.common.Constants;
import kr.co.test.common.ResponseCode;
import kr.co.test.rest.login.model.JwtAuthenticationToken;

public class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {
	
	private static final String DEFAULT_FILTER_PROCESSES_URL = "/api/**";
	
	public JwtAuthenticationTokenFilter() {
		super(DEFAULT_FILTER_PROCESSES_URL);
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value("#{jwt['jwt.tokenType']}")
	private String tokenType;
	
	@Value("#{jwt['jwt.header']}")
	private String header;
	
	@Autowired
	private JwtTokenComponent jwtTokenComponent;
	
	@Override
	protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
		return true;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		
		String sHeader = request.getHeader(this.header);
		JwtAuthenticationToken  jwtAuthenticationToken  = null;
		ResultSetMap resMap = new ResultSetMap();
		String sMessage = "";
		
//		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setCharacterEncoding(Constants.UTF8);
		
		if ( sHeader == null || !sHeader.startsWith(this.tokenType) ) {
			resMap.put(Constants.RESP.RESP_CD, ResponseCode.INVALID_TOKEN.getCode());
			resMap.put(Constants.RESP.RESP_MSG, ResponseCode.INVALID_TOKEN.getMessage());
			
			sMessage = JacksonUtil.converterMapToJsonStr(resMap);
			response.getWriter().write(sMessage);
			
		} else {
			String sToken = sHeader.substring(this.tokenType.length());
			
			try {
				jwtAuthenticationToken = new JwtAuthenticationToken(sToken);
				
			} catch (Exception e) {
				logger.error("", e);
				resMap.put(Constants.RESP.RESP_CD, ResponseCode.FFFFF.getCode());
				resMap.put(Constants.RESP.RESP_MSG, ResponseCode.FFFFF.getMessage());
			}
			
			Claims clams = null;
			
			try {
				clams = jwtTokenComponent.parseToken(sToken);
				
			} catch (ExpiredJwtException e) {
				resMap.put(Constants.RESP.RESP_CD, ResponseCode.TOKEN_EXPIRED.getCode());
				resMap.put(Constants.RESP.RESP_MSG, ResponseCode.TOKEN_EXPIRED.getMessage());
				
			} catch (Exception e) {
				logger.error("", e);
				resMap.put(Constants.RESP.RESP_CD, ResponseCode.FFFFF.getCode());
				resMap.put(Constants.RESP.RESP_MSG, ResponseCode.FFFFF.getMessage());
			}
			
			if ( clams == null ) {
				sMessage = JacksonUtil.converterMapToJsonStr(resMap);
				response.getWriter().write(sMessage);				
			}
		}
		return this.getAuthenticationManager().authenticate(jwtAuthenticationToken);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		super.successfulAuthentication(request, response, chain, authResult);
		chain.doFilter(request, response);
	}
	
}
