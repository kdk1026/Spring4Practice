package kr.co.test.page.manager.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import common.spring.resolver.ParamCollector;
import common.util.ArrayUtil;
import common.util.map.ResultSetMap;
import kr.co.test.common.Constants;
import kr.co.test.common.ResponseCode;

public class ManagerValidtion {
	
	private ManagerValidtion() {
		super();
	}

	private static String[] requiredSrchFields 	= {"srch_type", "srch_gbn", "draw"};
	private static String[] requiredSrchMsgPrefix 	= {"관리자 상태를", "관리자 검색을", "페이지 번호를"};
	private static String[] requiredSrchMsgPrefix2 	= {"관리자 상태", "관리자 검색", "페이지 번호"};
	
	private static String[] requiredFields 		= {"username", "password", "fullname", "enabled"};
	private static String[] requiredMsgPrefix 	= {"아이디를", "비밀번호를", "이름을", "계정상태를"};
	
	private static String[] fields 		= {"username", "password", "fullname", "belong", "tel", "enabled", "etc"};
	private static int[] filedsMaxByte 	= {30, 100, 20, 100, 13, 1, 255};
	private static String[] fieldNames 	= {"아이디", "비밀번호", "이름", "소속", "연락처(휴대폰)", "계정상태", "신청사유"};
	
	
	private static String[] removeArrayKey(String[] array, int nDelIdx) {
		List<String> list = new ArrayList<String>(Arrays.asList(array));
		
		list.remove(nDelIdx);
		String[] newArray = list.toArray(new String[0]);
		return newArray;
	}
	
	public static ResultSetMap processSrchValidtion(ParamCollector paramCollector) {
		ResultSetMap resMap = new ResultSetMap();
		String key = "";
		int i = 0;
		
		// 필수 값 체크
		for (String s : requiredSrchFields) {
			key = s;
			
			if ( !paramCollector.containsKey(key) || StringUtils.isBlank(paramCollector.getString(key)) ) {
				resMap.put(Constants.RESP.RESP_CD, ResponseCode.F0001.getCode());
				resMap.put(Constants.RESP.RESP_MSG, ResponseCode.F0001.getMessage(requiredSrchMsgPrefix[i]));
				break;
			}
			
			i ++;
		}
		
		// 유효 문자열 체크
		if (resMap.isEmpty()) {
			i = 0;
			
			for (String s : requiredSrchFields) {
				key = s;
				
				if ( !StringUtils.isBlank(paramCollector.getString(key)) ) {
					switch (i) {
					case 0:
						if ( !paramCollector.getString(key).matches("^[1|0|99]+$") ) {
							resMap.put(Constants.RESP.RESP_CD, ResponseCode.F0005.getCode());
							resMap.put(Constants.RESP.RESP_MSG, ResponseCode.F0005.getMessage(requiredSrchMsgPrefix2[i]));
						}
						break;
					case 1:
						String[] sSrchGbns = {"id", "name", "all"};
						List<String> srchGbns = ArrayUtil.arrayToList(sSrchGbns);
						if ( !srchGbns.contains( paramCollector.getString(key) ) ) {
							resMap.put(Constants.RESP.RESP_CD, ResponseCode.F0005.getCode());
							resMap.put(Constants.RESP.RESP_MSG, ResponseCode.F0005.getMessage(requiredSrchMsgPrefix2[i]));
							break;
						}
						break;
					case 2:
						if ( !paramCollector.getString(key).matches("^[0-9]+$") ) {
							resMap.put(Constants.RESP.RESP_CD, ResponseCode.F0005.getCode());
							resMap.put(Constants.RESP.RESP_MSG, ResponseCode.F0005.getMessage(requiredSrchMsgPrefix2[i]));
						}
						break;
					default:
						break;
					}
				}
				
				i ++;
			}
		}
		
		return resMap;
	}
	
	/**
	 * 관리자 관리 유효성 체크
	 * @param paramCollector
	 * @param charset (utf-8, euc-kr)
	 * @param isPwdContainsOnly (true, false)
	 * @param isUsernameOnly (true, false)
	 * @return
	 */
	public static ResultSetMap processValidtion(ParamCollector paramCollector, String charset, boolean isPwdContainsOnly, boolean isUsernameOnly) {
		ResultSetMap resMap = new ResultSetMap();
		String key = "";
		int i = 0;
		int nLen = 0;
		int nKorByte = (charset.equals("utf-8")) ? 3 : 2;
		StringBuilder sb = new StringBuilder();
		
		// 필수 값 체크
		if (isUsernameOnly) {
			key = requiredFields[0];
			
			if ( !paramCollector.containsKey(key) || StringUtils.isBlank(paramCollector.getString(key)) ) {
				resMap.put(Constants.RESP.RESP_CD, ResponseCode.F0001.getCode());
				resMap.put(Constants.RESP.RESP_MSG, ResponseCode.F0001.getMessage(requiredMsgPrefix[i]));
			}
			
		} else {
			String[] newArray = null;
			if (isPwdContainsOnly) {
				newArray = removeArrayKey(requiredFields, 1);
				requiredFields = newArray;
				
				newArray = removeArrayKey(requiredMsgPrefix, 1);
				requiredMsgPrefix = newArray; 
			}
			
			for (String s : requiredFields) {
				key = s;
				
				if ( !paramCollector.containsKey(key) || StringUtils.isBlank(paramCollector.getString(key)) ) {
					resMap.put(Constants.RESP.RESP_CD, ResponseCode.F0001.getCode());
					resMap.put(Constants.RESP.RESP_MSG, ResponseCode.F0001.getMessage(requiredMsgPrefix[i]));
					break;
				}
				
				i ++;
			}
		}

		// 유효 문자열 체크
		if (resMap.isEmpty()) {
			i = 0;
			sb.setLength(0);
			
			for (String s : fields) {
				key = s;
				
				if ( !StringUtils.isBlank(paramCollector.getString(key)) ) {
					switch (i) {
					case 0:
						if ( !paramCollector.getString(key).matches("^[a-zA-Z0-9]+$") ) {
							sb.append(ResponseCode.F0005.getMessage(fieldNames[i]));
							sb.append("<br/>").append(Constants.MESSAGE.RETRY);
							
							resMap.put(Constants.RESP.RESP_CD, ResponseCode.F0005.getCode());
							resMap.put(Constants.RESP.RESP_MSG, sb.toString());
						}
						break;
					case 1:
						if ( paramCollector.getString(key).matches("[가-힣]+$") ) {
							sb.append(ResponseCode.F0005.getMessage(fieldNames[i]));
							sb.append("<br/>").append(Constants.MESSAGE.RETRY);
							
							resMap.put(Constants.RESP.RESP_CD, ResponseCode.F0005.getCode());
							resMap.put(Constants.RESP.RESP_MSG, sb.toString());			
						}
						break;
					case 4:
						if ( !paramCollector.getString(key).matches("^(01[016789])-(\\d{3,4})-(\\d{4})+$") ) {
							sb.append(ResponseCode.F0005.getMessage(fieldNames[i]));
							sb.append("<br/>").append(Constants.MESSAGE.RETRY);
							
							resMap.put(Constants.RESP.RESP_CD, ResponseCode.F0005.getCode());
							resMap.put(Constants.RESP.RESP_MSG, sb.toString());				
						}
						break;
					case 5:
						if ( !paramCollector.getString(key).matches("^[0|1]+$") ) {
							sb.append(ResponseCode.F0005.getMessage(fieldNames[i]));
							sb.append("<br/>").append(Constants.MESSAGE.RETRY);
							
							resMap.put(Constants.RESP.RESP_CD, ResponseCode.F0005.getCode());
							resMap.put(Constants.RESP.RESP_MSG, sb.toString());				
						}
						break;
					case 6:
						break;
					default:
						if ( !paramCollector.getString(key).matches("^[가-힣]+$") ) {
							sb.append(ResponseCode.F0005.getMessage(fieldNames[i]));
							sb.append("<br/>").append(Constants.MESSAGE.RETRY);
							
							resMap.put(Constants.RESP.RESP_CD, ResponseCode.F0005.getCode());
							resMap.put(Constants.RESP.RESP_MSG, sb.toString());				
						}
						break;
					}
				}
				
				i ++;
			}
		}
		
		// 바이트 체크
		if (resMap.isEmpty()) {
			i = 0;
			sb.setLength(0);
			
			for (String s : fields) {
				key = s;
				
				if ( !StringUtils.isBlank(paramCollector.getString(key)) ) {
					try {
						nLen = paramCollector.getString(key).getBytes(charset).length;
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					
					if (nLen > filedsMaxByte[i]) {
						sb.append(ResponseCode.F0004.getMessage(fieldNames[i], filedsMaxByte[i], nKorByte));
						sb.append("<br/>").append(Constants.MESSAGE.RETRY);
						
						resMap.put(Constants.RESP.RESP_CD, ResponseCode.F0004.getCode());
						resMap.put(Constants.RESP.RESP_MSG, sb.toString());
						break;
					}
				}
				
				i ++;
			}
		}
		
		return resMap;
	}
	
}
