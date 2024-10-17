package kr.co.test.common.spring.component;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import common.LogDeclare;

@Component
public class InitializerComponent extends LogDeclare {

	private static final int ARRAY_SIZE = 10;
	private String[] sNumbers = new String[ARRAY_SIZE];
	
	public String[] getsNumbers() {
		return this.sNumbers;
	}
	
	public String getsNumber(int idx) {
		return this.sNumbers[idx];
	}
	
	@PostConstruct
	private void setsNumbers() {
		logger.debug("=== PostConstruct start ===");
		
		for (int i=0; i < ARRAY_SIZE; i++) {
			this.sNumbers[i] = String.format("%02d", (i+1));
		}
		
		logger.debug("=== PostConstruct end ===");
	}
	
}
