package kr.co.test.page.manager.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import common.util.map.ParamMap;
import common.util.map.ResultSetMap;

@Mapper
public interface ManagerMapper {
	
	/**
	 * 관리자 목록 개수 조회
	 * @param paramMap
	 * @return
	 */
	public int selectManagerCount(ParamMap paramMap);

	/**
	 * 관리자 목록 조회
	 * @param paramMap
	 * @return
	 */
	public List<ResultSetMap> selectManagerList(ParamMap paramMap);
	
	/**
	 * 관리자 아이디 유무 조회
	 * @param paramMap
	 * @return
	 */
	public ResultSetMap selectCheckUserName(ParamMap paramMap);
	
	/**
	 * 관리자 계정 등록
	 * @param paramMap
	 */
	public void insertManagerAccount(ParamMap paramMap);
	
	/**
	 * 관리자 부가정보 등록
	 * @param paramMap
	 */
	public void insertManagerInfo(ParamMap paramMap);
	
	/**
	 * 관리자 부가정보 삭제
	 * @param paramMap
	 */
	public void deleteManagerInfo(ParamMap paramMap);
	
	/**
	 * 관리자 계정 삭제
	 * @param paramMap
	 */
	public void deleteManagerAccount(ParamMap paramMap);
	
	/**
	 * 관리자 조회
	 * @param paramMap
	 * @return
	 */
	public ResultSetMap selectManagerInfo(ParamMap paramMap);
	
	/**
	 * 관리자 부가정보 수정
	 * @param paramMap
	 */
	public void updateManagerInfo(ParamMap paramMap);
	
	/**
	 * 관리자 계정 수정
	 * @param paramMap
	 */
	public void updateManagerAccount(ParamMap paramMap);

}
