package kr.co.test.rest.login.service;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import common.LogDeclare;
import common.spring.resolver.ParamCollector;
import common.util.crypto.RsaCryptoUtil;
import common.util.crypto.RsaCryptoUtil.Generate;
import common.util.date.JodaTimeDateUtil;
import common.util.file.FileUtil;
import common.util.map.ResultSetMap;
import io.jsonwebtoken.Claims;
import kr.co.test.common.Constants;
import kr.co.test.common.ResponseCode;
import kr.co.test.page.login.model.AuthenticatedUser;
import kr.co.test.page.login.service.UserService;
import kr.co.test.rest.login.security.JwtTokenComponent;
import kr.co.test.rest.login.security.JwtTokenComponent.JwtToken;

@Service
public class RestLoginServiceImpl extends LogDeclare implements RestLoginService {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtTokenComponent jwtTokenComponent;
	
	@Value("#{jwt}")
	private Properties jwtProp;
	
	@Override
	public ResultSetMap processAuth(ParamCollector paramCollector) {
		ResultSetMap resMap = new ResultSetMap();
		
		String username = paramCollector.getString(Constants.ID_PWD.USERNAME);
        String password = paramCollector.getString(Constants.ID_PWD.PASSWORD);
//        String decryptPwd = this.decryptRsa(password);
        
		AuthenticatedUser user = null;
		
		try {
			user = (AuthenticatedUser) userService.loadUserByUsername(username);
			
			if (user == null) {
				throw new RuntimeException("계정정보 없음");
			}
			
        	if ( !passwordEncoder.matches(password, user.getPassword()) ) {
        		throw new BadCredentialsException("비밀번호 불일치");
        	}
        	
        	if ( user.getLock() == 0 ) {
        		resMap.put(Constants.RESP.RESP_CD, ResponseCode.ACCESS_DENIED.getCode());
    			resMap.put(Constants.RESP.RESP_MSG, ResponseCode.ACCESS_DENIED.getMessage());
    			return resMap;
        	}
        	
        	JwtToken jwtToken = jwtTokenComponent.generateToken(user);
    		Date date = jwtTokenComponent.getExpirationTime(Integer.parseInt(jwtProp.getProperty("jwt.accessExpireHour")));
    		
    		resMap.put("token_type", jwtProp.getProperty("jwt.tokenType"));
    		resMap.put("access_token", jwtToken.accessToken);
    		resMap.put("expires_in", (date.getTime() / 1000));
    		resMap.put("refresh_token", jwtToken.refreshToken);
    		
    		List<String> listAuthority = new ArrayList<String>();
    		List<GrantedAuthority> authorities = (List<GrantedAuthority>) user.getAuthorities();
    		for (GrantedAuthority auth : authorities) {
    			listAuthority.add(auth.getAuthority());
    		}
    		resMap.put("scope", listAuthority);
    		
    		resMap.put(Constants.RESP.RESP_CD, ResponseCode.S0000.getCode());
    		resMap.put(Constants.RESP.RESP_MSG, ResponseCode.S0000.getMessage());
			
		} catch (Exception e) {
			logger.error("", e);
			resMap.put(Constants.RESP.RESP_CD, ResponseCode.LOGIN_INVALID.getCode());
			resMap.put(Constants.RESP.RESP_MSG, ResponseCode.LOGIN_INVALID.getMessage());
		}
		
		return resMap;
	}

	@Override
	public ResultSetMap processRefresh(ParamCollector paramCollector) {
		ResultSetMap resMap = new ResultSetMap();
		
		String header = jwtProp.getProperty("jwt.header");
		String tokenType = jwtProp.getProperty("jwt.tokenType");
		
		String sHeader = paramCollector.getRequest().getHeader(header);
		String sToken = sHeader.substring(tokenType.length());
		
		Claims clams = null;
		AuthenticatedUser user = null;
		
		try {
			clams = jwtTokenComponent.parseToken(sToken);
			user = jwtTokenComponent.parseClaims(clams);
			user.setAuthorities(userService.getAuthorities(user.getUsername()));
			
			JwtToken jwtToken = new JwtToken();
			jwtToken.accessToken = jwtTokenComponent.generateAccessToken(user);
			
			Date date = jwtTokenComponent.getExpirationTime(Integer.parseInt(jwtProp.getProperty("jwt.accessExpireHour")));
    		resMap.put("token_type", jwtProp.getProperty("jwt.tokenType"));
    		resMap.put("access_token", jwtToken.accessToken);
    		resMap.put("expires_in", (date.getTime() / 1000));
			
			Date expireDate = clams.getExpiration();
			String sExpireDate = JodaTimeDateUtil.Convert.getDateToString(expireDate);
			int nGap = JodaTimeDateUtil.GetDateInterval.intervalDays(sExpireDate);
			
			if (nGap >= -7 && nGap <= 0) {
				jwtToken.refreshToken = jwtTokenComponent.generateRefreshToken(user);
				resMap.put("refresh_token", jwtToken.refreshToken);
			}
			
		} catch (Exception e) {
			logger.error("", e);
		}
		
		return resMap;
	}
	
	private String decryptRsa(String password) {
		String sDestFilePath = "C:/test/rsa";
		String sPublicKeyFileName = "public.key";
		String sPrivateKeyFileName = "private.key";
		String sPrivateSignFileName = "private.sig";
		
		byte[] encodedPublicKey = FileUtil.convertFileToBytes(sDestFilePath + FileUtil.FOLDER_SEPARATOR + sPublicKeyFileName);
		byte[] encodedPrivateKey = FileUtil.convertFileToBytes(sDestFilePath + FileUtil.FOLDER_SEPARATOR + sPrivateKeyFileName);
		
		String decryptText = RsaCryptoUtil.Decrypt.decrypt(password, encodedPrivateKey);
		String sSign = FileUtil.readFile(sDestFilePath + FileUtil.FOLDER_SEPARATOR + sPrivateSignFileName);
		
		PublicKey publicKey = Generate.generatePublicKey(encodedPublicKey);
		boolean isVerify = RsaCryptoUtil.verifySignature(decryptText, sSign, publicKey);
		
		return (isVerify) ? decryptText : "";
	}
	
}
