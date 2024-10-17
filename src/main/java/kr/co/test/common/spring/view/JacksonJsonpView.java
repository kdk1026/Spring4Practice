package kr.co.test.common.spring.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

public class JacksonJsonpView extends MappingJackson2JsonView {
	
	@Override
	protected Object filterModel(Map<String, Object> model) {
		Object result = super.filterModel(model);
		
		if ( !(result instanceof Map) ) {
			return result;
		}
		
		@SuppressWarnings("unchecked")
		Map<String, Object> map = (Map<String, Object>) result;
		
		return map;
	}

	@Override
	public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String callback = (String) request.getAttribute("myCallback");
		response.getOutputStream().write(new String(callback + "(").getBytes());
		
		super.render(model, request, response);
		
		response.getOutputStream().write(new String(");").getBytes());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
	}
	
}
