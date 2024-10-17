package kr.co.test.common.exception;

public class RestException extends Exception {

	private static final long serialVersionUID = 1L;

	public RestException() {
		super();
	}

	public RestException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public RestException(String arg0) {
		super(arg0);
	}

	public RestException(Throwable arg0) {
		super(arg0);
	}
	
}
