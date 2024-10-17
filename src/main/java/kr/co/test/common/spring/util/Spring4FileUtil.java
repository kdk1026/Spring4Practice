package kr.co.test.common.spring.util;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import common.BaseObject;
import common.util.ResponseUtil;
import common.util.file.FileUtil;
import common.util.file.NioFileUtil;
import common.util.string.StringUtilsSub;

public class Spring4FileUtil {

	private Spring4FileUtil() {
		super();
	}
	
	private static final Logger logger = LoggerFactory.getLogger(Spring4FileUtil.class);
	
	public static class FileVO extends BaseObject {
		
		private static final long serialVersionUID = 1L;

		public FileVO() {
			super();
		}
		
		/**
		 * 파일 경로
		 */
		public String destFilePath;
		
		/**
		 * 파일 확장자
		 */
		public String fileExt;
		
		/**
		 * 원파일명
		 */
		public String orignlFileNm;
		
		/**
		 * 저장파일명
		 */
		public String saveFileNm;
		
		/**
		 * 파일 크기
		 */
		public long fileSize;
		
		/**
		 * 파일 크기 단위
		 */
		public String fileSizeUnits;
	}
	
	/**
	 * <pre>
	 * Spring 4 파일 업로드
	 *  - Java 7 NIO API
	 *  - 파일 업로드 전 파일 확장자 및 MIME Type 체크 진행할 것
	 * </pre>
	 * @param multipartFile
	 * @param destFilePath
	 * @return
	 */
	public static FileVO uploadFile(MultipartFile multipartFile, String destFilePath) {
		destFilePath = (destFilePath.replaceAll("^(.*)(.$)", "$2").equals("/")) ? destFilePath : (destFilePath + FileUtil.FOLDER_SEPARATOR);
		
		Path destPath = Paths.get(destFilePath);
		
		if ( !destPath.toFile().exists() ) {
			try {
				Files.createDirectories(destPath);
				
			} catch (IOException e) {
				logger.error("", e);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		String fileExt = NioFileUtil.EXTENSION_SEPARATOR + NioFileUtil.getFileExtension(multipartFile.getOriginalFilename());
		
		sb.append(StringUtilsSub.getRandomString()).append(fileExt);
		String saveFileNm = sb.toString();
		
		sb.setLength(0);
		sb.append(destFilePath).append(NioFileUtil.FOLDER_SEPARATOR).append(saveFileNm);
		
		FileVO fileVO = null;
		
		try {
			byte[] bytes = multipartFile.getBytes();
            Path path = Paths.get(sb.toString());
            Files.write(path, bytes);
			
			fileVO = new FileVO();
			fileVO.destFilePath = destFilePath;
			fileVO.orignlFileNm = multipartFile.getOriginalFilename();
			fileVO.saveFileNm = saveFileNm;
			fileVO.fileExt = fileExt;
			fileVO.fileSize = multipartFile.getSize();
			fileVO.fileSizeUnits = NioFileUtil.readableFileSize(fileVO.fileSize);
			
		} catch (IllegalStateException | IOException e) {
			logger.error("", e);
		}
		
		return fileVO;
	}
	
	/**
	 * Spring 4 파일 다운로드
	 * @param fileVO
	 * @param request
	 * @param response
	 */
	public static void downloadFile(FileVO fileVO, HttpServletRequest request, HttpServletResponse response) {
		String downloadlFileNm = "";
		
		String destFilePath = fileVO.destFilePath;
		destFilePath = (destFilePath.replaceAll("^(.*)(.$)", "$2").equals("/")) ? destFilePath : (destFilePath + NioFileUtil.FOLDER_SEPARATOR);
		
		String saveFileNm = fileVO.saveFileNm;
		String orignlFileNm = fileVO.orignlFileNm;
		
		if ( !StringUtils.hasText(orignlFileNm) ) {
			downloadlFileNm = ResponseUtil.contentDisposition(request, saveFileNm);
		} else {
			downloadlFileNm = ResponseUtil.contentDisposition(request, orignlFileNm);
		}
		
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Transfer-Encoding", "binary;");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + downloadlFileNm + "\";");
		
		Path source = Paths.get(destFilePath + saveFileNm);

		try ( OutputStream os = response.getOutputStream() ) {
			Files.copy(source, os);

		} catch (Exception e) {
			logger.error("", e);
		}
	}
	
	/**
	 * Spring MultipartFile -> Java File 변환
	 * @param multipart
	 * @return
	 */
	public static File multipartFileToFile(MultipartFile multipartFile) {
		File convFile = new File(multipartFile.getOriginalFilename());
		
		try {
			multipartFile.transferTo(convFile);
			
		} catch (IllegalStateException | IOException e) {
			logger.error("", e);
		}
		
        return convFile;
	}
	
}
