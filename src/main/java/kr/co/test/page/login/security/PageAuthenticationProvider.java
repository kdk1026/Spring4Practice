package kr.co.test.page.login.security;

import java.security.PrivateKey;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import common.LogDeclare;
import common.util.crypto.RsaCryptoUtil;
import kr.co.test.page.login.model.AuthenticatedUser;
import kr.co.test.page.login.service.UserService;

@Component
public class PageAuthenticationProvider extends LogDeclare implements AuthenticationProvider {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        String decryptPwd = this.decryptRsa(password);
        
        AuthenticatedUser user = null;
        Collection<GrantedAuthority> authorities = null;
        
        try {
        	user = (AuthenticatedUser) userService.loadUserByUsername(username);
        	
        	if ( !passwordEncoder.matches(decryptPwd, user.getPassword()) ) {
        		throw new BadCredentialsException("비밀번호 불일치");
        	}
        	
        	authorities = user.getAuthorities();
			
		} catch (UsernameNotFoundException e) {
			logger.error("", e);
			throw new UsernameNotFoundException(e.getMessage());
		} catch (BadCredentialsException e) {
			logger.error("", e);
			throw new BadCredentialsException(e.getMessage());
		} catch (Exception e) {
			logger.error("", e);
			throw new RuntimeException(e.getMessage());
		}

		return new UsernamePasswordAuthenticationToken(user, decryptPwd, authorities);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	
	private String decryptRsa(String password) {
		ServletRequestAttributes attr = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request	= attr.getRequest();
		HttpSession session = request.getSession(false);
		
		PrivateKey privateKey = RsaCryptoUtil.Session.getPrivateKeyInSession(session);
		
		return RsaCryptoUtil.Decrypt.decryptFromJsbn(password, privateKey.getEncoded());
	}
	
}
