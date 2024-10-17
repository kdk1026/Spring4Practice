package kr.co.test.common;

import java.text.MessageFormat;

public enum ResponseCode {
	
	S0000("0000", "정상처리"),
	F0001("0001", "{0} 입력하여 주세요."),
	F0002("0002", "{0} {1} 형식에 맞게 입력하세요."),
	F0003("0003", "데이터 처리 중 오류가 발생하였습니다."),
	
	F0004("0004", "{0} - 최대 {1} byte를 초과할 수 없습니다.\n(영문, 숫자, 특수문자, 공백 1 byte / 그 외 {2} byte"),
	F0005("0005", "{0} 에 유효하지 않은 문자열이 있습니다."),
	
	FILE_SIZE_LIMIT("0006", "첨부하려는 파일의 크기가 서버에서 허용하는 크기보다 큽니다."),
	FILE_IS_NOT_TYPE("0007", "허용되는 파일의 종류가 아닙니다."),
	
	LOGIN_INVALID("1001", "아이디 또는 비밀번호를 다시 확인해주세요."),
	ACCESS_DENIED("8888", "접근 권한이 없습니다."),
	
	INVALID_TOKEN("9999", "Invalid Access"),
	TOKEN_EXPIRED("9998", "Token Expired"),
	
	FFFFF("FFFF", "시스템 오류")
	;
	
	private String code;
	private String message;
	
	private ResponseCode(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public String getCode() {
		return code;
	}
	
	public String getMessage() {
		return message;
	}

	public String getMessage(Object ... arguments) {
		return MessageFormat.format(message, arguments);
	}
	
}
