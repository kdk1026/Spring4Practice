package kr.co.test.page.file.service;

import org.springframework.web.multipart.MultipartFile;

import common.spring.resolver.ParamCollector;
import common.util.file.FileUtil;
import common.util.file.NioFileTypeUtil;
import common.util.map.ResultSetMap;
import kr.co.test.common.Constants;
import kr.co.test.common.ResponseCode;

public class FileValidation {

	private FileValidation() {
		super();
	}
	
	public static ResultSetMap processValidtion(ParamCollector paramCollector, boolean isDocFileCheck, boolean isImgFileCheck, long limitSize) {
		ResultSetMap resMap = new ResultSetMap();
		String key = "file";
		
		MultipartFile multipartFile = paramCollector.getMultipartFile(key);

		String fileExt = "";
		String mimeType = "";
		String orignlFileNm = "";
		long fileSize = 0;
		
		if (multipartFile != null) {
			orignlFileNm = multipartFile.getOriginalFilename();
			fileExt = FileUtil.getFileExtension(orignlFileNm);
			fileSize = multipartFile.getSize();
			mimeType = multipartFile.getContentType();
		}
		
		boolean isDocFile = false;
		if (isDocFileCheck) {
			isDocFile = NioFileTypeUtil.isDocFile(fileExt, mimeType);

			if (!isDocFile) {
				resMap.put(Constants.RESP.RESP_CD, ResponseCode.FILE_IS_NOT_TYPE.getCode());
				resMap.put(Constants.RESP.RESP_MSG, ResponseCode.FILE_IS_NOT_TYPE.getMessage());
				return resMap;
			}
		}
		
		boolean isImgFile = false;
		if (isImgFileCheck) {
			isImgFile = NioFileTypeUtil.isImgFile(fileExt, mimeType);
			
			if (!isImgFile) {
				resMap.put(Constants.RESP.RESP_CD, ResponseCode.FILE_IS_NOT_TYPE.getCode());
				resMap.put(Constants.RESP.RESP_MSG, ResponseCode.FILE_IS_NOT_TYPE.getMessage());
				return resMap;
			}
		}
		
		if (fileSize > limitSize) {
			resMap.put(Constants.RESP.RESP_CD, ResponseCode.FILE_SIZE_LIMIT.getCode());
			resMap.put(Constants.RESP.RESP_MSG, ResponseCode.FILE_SIZE_LIMIT.getMessage());
		}
		
		return resMap;
	}
	
}
