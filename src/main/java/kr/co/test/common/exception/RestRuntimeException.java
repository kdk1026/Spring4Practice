package kr.co.test.common.exception;

public class RestRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RestRuntimeException() {
		super();
	}

	public RestRuntimeException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public RestRuntimeException(String arg0) {
		super(arg0);
	}

	public RestRuntimeException(Throwable arg0) {
		super(arg0);
	}
	
}
