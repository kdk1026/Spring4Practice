package kr.co.test.common.spring.controller_advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import common.util.map.ResultSetMap;
import kr.co.test.common.Constants;
import kr.co.test.common.ResponseCode;
import kr.co.test.common.exception.RestException;
import kr.co.test.common.exception.RestRuntimeException;

@ControllerAdvice
public class ExceptionControllerAdvice {
	
	/**
	 * <pre>
	 * mvc-config.xml 의 multipartResolver 속성으로 maxUploadSize 지정 시 가능
	 *  - 수기 설정 시 가능 / properties 이용 시 불가
	 *    > long 형이어야 하나 properties는 String형
	 *    > 단, XmlConfig가 아닌 JavaConfig는 가능 (Java는 형 변환 가능하므로)
	 * </pre>
	 * @param e
	 * @param redirectAttributes
	 * @return
	 */
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ModelAndView maxUploadSizeExceededException(MaxUploadSizeExceededException e, RedirectAttributes redirectAttributes) {
		ModelAndView mav = new ModelAndView();
		
		ResultSetMap resMap = new ResultSetMap();
		resMap.put(Constants.RESP.RESP_CD, ResponseCode.FILE_SIZE_LIMIT.getCode());
		resMap.put(Constants.RESP.RESP_MSG, ResponseCode.FILE_SIZE_LIMIT.getMessage());
		
		redirectAttributes.addAttribute(Constants.RESP.RESP, resMap);
		mav.setViewName("redirect:/file");
		
		return mav;
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ModelAndView exception (Exception e) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e);
		mav.setViewName("test/error/exception");
		  
		return mav;
	}
	
	@ExceptionHandler(RestException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ModelAndView restException (Exception e) {
		ModelAndView mav = new ModelAndView();
		mav.addObject(Constants.RESP.RESP_CD, ResponseCode.FFFFF.getCode());
		mav.addObject(Constants.RESP.RESP_MSG, ResponseCode.FFFFF.getMessage());
		mav.setViewName("jsonView");
		
		return mav;
	}
	
	@ExceptionHandler(RestRuntimeException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ModelAndView restRuntimeException (Exception e) {
		ModelAndView mav = new ModelAndView();
		mav.addObject(Constants.RESP.RESP_CD, ResponseCode.FFFFF.getCode());
		mav.addObject(Constants.RESP.RESP_MSG, ResponseCode.FFFFF.getMessage());
		mav.setView(new MappingJackson2JsonView());
		
		return mav;
	}
	
}
