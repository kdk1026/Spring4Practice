package kr.co.test;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import common.util.crypto.AesCryptoUtil;

public class PwdCrytoTest {

	@Test
	public void test() {
		System.out.println( RandomStringUtils.randomAlphanumeric(16)  );
		
		String sKey = "2Nod3bc8TZvzfy4i";
		String sPadding = AesCryptoUtil.AES_CBC_PKCS5PADDING;
		String sPliaText = "sa";
		
		System.out.println( AesCryptoUtil.aesEncrypt(sKey, sPadding, sPliaText) );
		
		
		System.out.println( AesCryptoUtil.aesDecrypt(sKey, sPadding, "+41KL3RnItJ+XXF7dKaz2g==")  );
		
		
		System.out.println();
		String sPlain = "admin!@34";
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		System.out.println( passwordEncoder.encode(sPlain) );
		
		//1 -- $2a$10$exYnqfvM6GD1M.3mcpKY5eyquINLpam7/7l2DkofCe0ecJlFJD2Pa
		//2 -- $2a$10$zYjTk3EDtiaBqnsa8aKvre63PDzUmrxRdTVAyaTExEdcI6IfE6sTK
		
		String s1 = "admin!@34";
		String s2 = "$2a$10$zYjTk3EDtiaBqnsa8aKvre63PDzUmrxRdTVAyaTExEdcI6IfE6sTK";
		System.out.println( passwordEncoder.matches(s1, s2) );
		
	}
	
}

