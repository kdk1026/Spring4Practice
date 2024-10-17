package common.spring;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

/**
 * Spring 4.2 이상
 */
public class RestCorsConfig {
	
	private RestCorsConfig() {
		super();
	}

	public static CorsConfigurationSource configurationSource() {
		return new CorsConfigurationSource() {

			@Override
			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
				CorsConfiguration cors = new CorsConfiguration();
				cors.addAllowedOrigin("*");
				cors.addAllowedMethod("POST, GET");
				cors.setMaxAge(3600L);
				cors.addAllowedHeader("Content-Type, Accept, x-requested-with, Authorization");
				return cors;
			}
		};
	}
	
}
