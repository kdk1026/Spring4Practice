package kr.co.test.common.db;

import java.util.Properties;

import com.zaxxer.hikari.HikariConfig;

import common.util.crypto.AesCryptoUtil;
import common.util.properties.PropertiesUtil;

public class CustomHikariConfig extends HikariConfig {

	@Override
	public void setPassword(String password) {
		super.setPassword(decode(password));
	}

	private String decode(String password) {
		Properties prop = PropertiesUtil.getPropertiesClasspath("jdbc.properties");
		
		String sKey = prop.getProperty("jdbc.password.encrypt.key");
		String sPadding = AesCryptoUtil.AES_CBC_PKCS5PADDING;
		
		return AesCryptoUtil.aesDecrypt(sKey, sPadding, password);
	}
	
}
