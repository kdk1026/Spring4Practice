package kr.co.test.common.spring.component.aspect;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import common.LogDeclare;
import common.spring.resolver.ParamCollector;
import common.util.crypto.RsaCryptoUtil;

@Component
@Aspect
public class RsaAspect extends LogDeclare {

	@Pointcut("@annotation(common.annotation.RsaKeyGenerator)")
	void RsaKeyGenerator() {}
	
	@Pointcut("@annotation(common.annotation.RsaDecrypt)")
	void RsaDecrypt() {}
	
	private static final String PWD = "password";
	
	@Around("RsaKeyGenerator()")
	public Object key_trace(ProceedingJoinPoint jp) throws Throwable {

		HttpServletRequest request	= ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();

        KeyPair keyPair = RsaCryptoUtil.Generate.generateKeyPair();
        PrivateKey privateKey = RsaCryptoUtil.Generate.generatePrivateKey(keyPair);
        PublicKey publicKey = RsaCryptoUtil.Generate.generatePublicKey(keyPair);
        
        HttpSession session = request.getSession();
        
        RsaCryptoUtil.Session.setPrivateKeyInSession(session, privateKey);
        RsaCryptoUtil.Session.setPublicKeySpecInSession(session, publicKey);
        
		return jp.proceed();
	}

	@Around("RsaDecrypt()")
	public Object decrypt_trace(ProceedingJoinPoint jp) throws Throwable {

		Object[] values = jp.getArgs();

		HttpServletRequest request	= ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();

        ParamCollector paramCollector = new ParamCollector();
        for (Object obj : values) {
        	if (obj instanceof ParamCollector) {
        		paramCollector = (ParamCollector)obj;
        	}
        }

		HttpSession session = request.getSession(false);
		PrivateKey privateKey = RsaCryptoUtil.Session.getPrivateKeyInSession(session);
		
		if ( paramCollector.containsKey(PWD) ) {
			String rsaDecryptedPwd = RsaCryptoUtil.Decrypt.decrypt(paramCollector.getString(PWD), privateKey.getEncoded());
			paramCollector.put(PWD, rsaDecryptedPwd);
		}

    	return jp.proceed();
	}
	
}
