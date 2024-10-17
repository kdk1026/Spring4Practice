package kr.co.test.common;

import java.nio.charset.Charset;

public class Constants {
	
	private Constants() {
		super();
	}
	
	public static final String UTF8 = Charset.forName("utf-8").toString();
	public static final String RSA_PUBLIC = "rsaPublic";

	public static class MESSAGE {
		public static final String MESSAGE = "message";
		public static final String LOGIN_INVALID = "아이디 또는 비밀번호를 다시 확인해주세요.";
		public static final String SESSION_EXPIRED = "세션이 만료되었습니다.<br/>다시 로그인 해주세요.";
		public static final String ALREADY_USE_USERNAME = "이미 사용 중인 아이디 입니다.";
		public static final String ALREADY_NOT_USE_USERNAME = "사용 가능한 아이디 입니다.";
		public static final String SAVE_ERROR_PREFIX = "<br/>잠시 후 다시 시도해주세요.<br/>문제가 지속된다면, 관리자에게 문의하시기 바랍니다.";
		public static final String IS_LOCKED = "계정이 잠금 상태입니다.<br/>관리자에게 문의하시기 바랍니다.";
		public static final String RETRY = "처음부터 다시 시도해주세요.";
	}
	
	public static class ALERT {
		public static final String ALERT = "alert";
		public static final String DANGER = "danger";
		public static final String WARNNING = "warning";
		public static final String INFO = "info";
	}
	
	public static class ID_PWD {
		public static final String SUPER_ADMIN = "admin";
		public static final String USERNAME = "username";
		public static final String PASSWORD = "password";
	}
	
	public static class RESP {
		public static final String RESP = "resp";
		public static final String RESP_CD = "resp_cd";
		public static final String RESP_MSG = "resp_msg";
	}
	
	public static class PERSONAL {
		public static final String NAME = "name";
		public static final String ENABLED = "enabled";
	}
	
	public static class ETC {
		public static final String SCOPE = "scope";
	}
	
}
