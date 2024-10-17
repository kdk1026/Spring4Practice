package kr.co.test.rest.login.security;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import kr.co.test.common.Constants;
import kr.co.test.page.login.model.AuthenticatedUser;

@Component
public class JwtTokenComponent {
	
	@Value("#{jwt}")
	private Properties jwtProp;
	
	public static class JwtToken {
		public String accessToken;
		public String refreshToken;
	}
	
	public JwtToken generateToken(AuthenticatedUser user) {
		JwtToken jwtToken = new JwtToken();
	 
		try {
			jwtToken.accessToken = this.generateAccessToken(user);
			jwtToken.refreshToken = this.generateRefreshToken(user);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	 
		return jwtToken; 
	}
	
	public String generateAccessToken(AuthenticatedUser user) throws Exception {
		JwtBuilder builder = Jwts.builder();
		builder.setId(user.getUsername())
	 		.setIssuedAt(new Date())
	 		.setSubject("AccessToken")
	 		.setIssuer(jwtProp.getProperty("jwt.issuer"))
	 		.signWith(SignatureAlgorithm.HS256, jwtProp.getProperty("jwt.secret"))
	 		.setExpiration(this.getExpirationTime(Integer.parseInt(jwtProp.getProperty("jwt.accessExpireHour"))));
	 	
	 	builder.claim(Constants.PERSONAL.NAME, user.getName());
	 	
		List<String> listAuthority = new ArrayList<String>();
		List<GrantedAuthority> authorities = (List<GrantedAuthority>) user.getAuthorities();
		for (GrantedAuthority auth : authorities) {
			listAuthority.add(auth.getAuthority());
		}
	 	builder.claim(Constants.ETC.SCOPE, listAuthority);
	 
	 	return builder.compact();
	}

	public String generateRefreshToken(AuthenticatedUser user) throws Exception {
		JwtBuilder builder = Jwts.builder();
		builder.setId(user.getUsername())
			 .setIssuedAt(new Date())
			 .setSubject("RefreshToken")
			 .setIssuer(jwtProp.getProperty("jwt.issuer"))
			 .signWith(SignatureAlgorithm.HS256, jwtProp.getProperty("jwt.secret"))
			 .setExpiration(this.getExpirationTime(Integer.parseInt(jwtProp.getProperty("jwt.refreshExpireHour"))));
		 
		builder.claim(Constants.PERSONAL.NAME, user.getName());
		 
		List<String> listAuthority = new ArrayList<String>();
		listAuthority.add(jwtProp.getProperty("jwt.refreshRole"));
	 	builder.claim(Constants.ETC.SCOPE, listAuthority);
		 
		return builder.compact();
	}
	
	public Date getExpirationTime(int expireIn) {
    	return DateTime.now().plusHours(expireIn).toDate();
    }
	
	public Claims parseToken(String token) throws Exception {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtProp.getProperty("jwt.secret"))
                .parseClaimsJws(token)
                .getBody();

        return claims;
    }
	
	@SuppressWarnings("unchecked")
	public AuthenticatedUser parseClaims(Claims clams) throws Exception {
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
