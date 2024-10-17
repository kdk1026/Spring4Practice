package kr.co.test.rest.login.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import common.util.properties.PropertiesUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import kr.co.test.common.Constants;
import kr.co.test.page.login.model.AuthenticatedUser;
import kr.co.test.rest.login.model.JwtAuthenticationToken;

public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public boolean supports(Class<?> authentication) {
		return JwtAuthenticationToken.class.isAssignableFrom(authentication);
	}

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		
		JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;
		String sToken = jwtAuthenticationToken.getToken();
		
		Claims clams = null;
		AuthenticatedUser user = null;
		
		try {
			clams = JwtTokenUtil.parseToken(sToken);
	        user = JwtTokenUtil.parseClaims(clams);
	        
		} catch (Exception e) {
			logger.error("", e);
		}
		
		/*
		if (user == null) {
			throw new JwtException("Token is not Valid");
		}
		*/
		
		return user;
	}
	
	private static class JwtTokenUtil {
		static Properties jwtProp = PropertiesUtil.getPropertiesClasspath("jwt.properties");
		
		public static Claims parseToken(String token) throws Exception {
	        Claims claims = Jwts.parser()
	                .setSigningKey(jwtProp.getProperty("jwt.secret"))
	                .parseClaimsJws(token)
	                .getBody();

	        return claims;
	    }
		
		@SuppressWarnings("unchecked")
		public static AuthenticatedUser parseClaims(Claims clams) throws Exception {
			AuthenticatedUser user = new AuthenticatedUser();
	        user.setUsername(clams.getId());
	        user.setName(clams.get(Constants.PERSONAL.NAME).toString());
	        
	        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	        List<String> listAuthority = (List<String>) clams.get(Constants.ETC.SCOPE);
	        for (String role : listAuthority) {
	        	authorities.add(new SimpleGrantedAuthority(role));
	        }
	        user.setAuthorities(authorities);
	        
	        return user;
		}
	}

}
