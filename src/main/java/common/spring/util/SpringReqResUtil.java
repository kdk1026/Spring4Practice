package common.spring.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class SpringReqResUtil {

	private SpringReqResUtil() {
		super();
	}

	public static HttpServletRequest getRequest() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		return attr.getRequest();
	}
	
	/**
	 * Java 1.7 Base
	 * @return
	 */
	public static HttpServletResponse getResponse() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		return attr.getResponse();
	}
	
}
