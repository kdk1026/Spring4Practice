package common.spring.csrf;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * A Spring MVC <code>HandlerInterceptor</code> which is responsible to enforce CSRF token validity on incoming posts requests. The interceptor
 * should be registered with Spring MVC servlet using the following syntax:
 * <pre>
 *   &lt;mvc:interceptors&gt;
 *        &lt;bean class="com.eyallupu.blog.springmvc.controller.csrf.CSRFHandlerInterceptor"/&gt;
 *   &lt;/mvc:interceptors&gt;
 *   </pre>
 * @author Eyal Lupu
 * @see CSRFRequestDataValueProcessor
 *
 */
public class CSRFHandlerInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		if (!request.getMethod().equalsIgnoreCase("POST") ) {
			// Not a POST - allow the request
			return true;
		} else {
			String contentType = request.getHeader("Content-Type");
			
			// This is a POST request - need to check the CSRF token
			String sessionToken = CSRFTokenManager.getTokenForSession(request.getSession());
			String requestToken = "";
			
			if ( MediaType.APPLICATION_FORM_URLENCODED_VALUE.equals(contentType) 
					|| (contentType.indexOf(MediaType.MULTIPART_FORM_DATA_VALUE) > -1) ) {
				requestToken = CSRFTokenManager.getTokenFromRequest(request);
			}
			
			if ( MediaType.APPLICATION_JSON_VALUE.equals(contentType) ) {
				requestToken = CSRFTokenManager.getTokenFromRequestHeader(request);
			}
			
			if (sessionToken.equals(requestToken)) {
				return true;
			} else {
				response.sendError(HttpServletResponse.SC_FORBIDDEN, "Bad or missing CSRF value");
				return false;
			}
		}
	}


}
