package config;

import java.nio.charset.StandardCharsets;

import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletRegistration.Dynamic;

import org.h2.server.web.WebServlet;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.navercorp.lucy.security.xss.servletfilter.XssEscapeServletFilter;

import common.servlet.filter.CorsFilter;
import common.servlet.filter.SessionCookieFilter;
import config.listener.SessionListener;
import config.spring.AppConfig;
import config.spring.MvcConfig;
import config.spring.security.SecurityConfig;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	private static final String SERVLET_MAPPING_URL = "/";
	private static final String UTF_8 = StandardCharsets.UTF_8.toString();
	
	private static final String H2_CONSOLE_MAPPING_URL = "/console/*";
	private static final String CORS_FILTER_URL_PATTERN = "/api/*";
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		super.onStartup(servletContext);
		servletContext.addListener(new SessionListener());
		servletContext.addListener(new RequestContextListener());
		
		servletContext.setInitParameter("webAppRootKey", "webapp.root");

		ServletRegistration.Dynamic h2Servlet
			= servletContext.addServlet("H2Console", new WebServlet());
		h2Servlet.addMapping(H2_CONSOLE_MAPPING_URL);
		
		// urlPattern 지정하는 경우, 필요 없는 경우 getServletFilters() 를 통해 간편 등록
		FilterRegistration.Dynamic filter = servletContext.addFilter("corsFilter", CorsFilter.class);
		filter.addMappingForUrlPatterns(null, false, CORS_FILTER_URL_PATTERN);
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {AppConfig.class, SecurityConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {MvcConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {SERVLET_MAPPING_URL};
	}

	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter(UTF_8, true);
		
		XssEscapeServletFilter xssEscapeServletFilter = new XssEscapeServletFilter();
		
		SiteMeshFilter sitemeshFilter = new SiteMeshFilter();
		
		// XXX : SecurityConfig 의 http.cors().configurationSource 참고 
// 		CorsFilter corsFilter = new CorsFilter();
		
		SessionCookieFilter sessionCookieFilter = new SessionCookieFilter();

		return new Filter[] {encodingFilter, xssEscapeServletFilter, sitemeshFilter, sessionCookieFilter};
	}

	@Override
	protected void customizeRegistration(Dynamic registration) {
		int maxFileSize = 5242880;			// 5MB
	    int maxRequestSize = 20971520;		// 20MB
	    int fileSizeThreshold = 0;

		MultipartConfigElement multipartConfigElement =
			new MultipartConfigElement(null, maxFileSize, maxRequestSize, fileSizeThreshold);

		registration.setMultipartConfig(multipartConfigElement);
	}

}
