package common.spring.resolver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import common.LogDeclare;
import common.util.json.JacksonUtil;
import common.util.map.ResultSetMap;

public class ParamMapArgResolver extends LogDeclare implements HandlerMethodArgumentResolver {
	
	/**
	 * Java 1.7 Base 
	 */
	private static final String UTF_8 = StandardCharsets.UTF_8.toString();

	@Override
	public Object resolveArgument(MethodParameter parameter,
			ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {

		ParamCollector paramCollector = new ParamCollector();

		this.setRequestHeader(webRequest);
		this.setRequestParameter(webRequest, paramCollector);
		this.setHttpServletRequest(webRequest, paramCollector);

		return paramCollector;
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return ParamCollector.class.isAssignableFrom(parameter.getParameterType());
	}
	
	private void setRequestHeader(NativeWebRequest webRequest) {

		HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
		ResultSetMap headerMap = new ResultSetMap();

		Enumeration<?> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			headerMap.put(key, value);
		}
	}

	private void setHttpServletRequest(NativeWebRequest webRequest, ParamCollector paramCollector) {
		HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
		paramCollector.setRequest(request);
	}

	private void setRequestParameter(NativeWebRequest webRequest, ParamCollector paramCollector) {

		HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
		String contentType = request.getHeader("Content-Type");

		if ( StringUtils.isEmpty(contentType) ) {
			this.setQueryStringParam(request, paramCollector);
		} else {
			if ( MediaType.APPLICATION_FORM_URLENCODED_VALUE.equals(contentType) ) {
				this.setFormParam(request, paramCollector);
			}
			
			if ( contentType.indexOf(MediaType.MULTIPART_FORM_DATA_VALUE) > -1 ) {
				this.setMultipartParam(request, paramCollector);
			}
			
			if ( MediaType.APPLICATION_JSON_VALUE.equals(contentType) ) {
				this.setJsonParam(request, paramCollector);
			}
		}
	}
	
	private void setQueryStringParam(HttpServletRequest request, ParamCollector paramCollector) {
		Enumeration<String> en = request.getParameterNames();
    	while (en.hasMoreElements()) {
    		String key = en.nextElement();
    		String value = request.getParameter(key);
			try {
				value = URLDecoder.decode(value, UTF_8);
			} catch (UnsupportedEncodingException e) {
				logger.error(e.getClass().getSimpleName());
				logger.debug(e.getCause().getMessage());
			}
    		paramCollector.put(key, value);
    	}
	}

	private void setFormParam(HttpServletRequest request, ParamCollector paramCollector) {
		Enumeration<String> en = request.getParameterNames();
    	while (en.hasMoreElements()) {
    		String key = en.nextElement();
    		String value = request.getParameter(key);
			String[] values = request.getParameterValues(key);

			if (values.length > 1) {
				paramCollector.put(key, Arrays.asList(values));
			} else {
				paramCollector.put(key, value);
			}
    	}

		if (request instanceof MultipartHttpServletRequest) {
			this.setMultipartParam(request, paramCollector);
		}
	}

    public void setMultipartParam(HttpServletRequest request, ParamCollector paramCollector) {
		MultipartHttpServletRequest multiReq = (MultipartHttpServletRequest) request;

		Iterator<String> it = multiReq.getFileNames();
		while (it.hasNext()) {
			String fileKey = it.next();
			List<MultipartFile> fileList = multiReq.getFiles(fileKey);

			if (fileList.size() > 1) {
				paramCollector.put(fileKey, fileList);
			} else {
				paramCollector.put(fileKey, fileList.get(0));
			}
		}
    }

    public void setJsonParam(HttpServletRequest request, ParamCollector paramCollector) {
        try {
        	BufferedReader buffer = new BufferedReader(new InputStreamReader(request.getInputStream(), UTF_8));
        	StringBuilder sb = new StringBuilder();

            String reqDataLine = "";

            while((reqDataLine = buffer.readLine()) != null) {
            	sb.append(reqDataLine);
            }
            buffer.close();

            if (sb.toString().startsWith("[")) {
            	paramCollector.put("JSONArray", JacksonUtil.converterJsonStrToList(sb.toString()));
            } else {
            	Map<String, Object> convertMap = JacksonUtil.converterJsonStrToMap(sb.toString());
            	paramCollector.putAll(convertMap);
            }

        } catch (Exception e) {
			logger.error(e.getClass().getSimpleName());
			logger.debug(e.getCause().getMessage());
		}
    }

}
