package kr.co.test.page.manager.service;

import java.util.List;

import common.spring.resolver.ParamCollector;
import common.util.map.ResultSetMap;

public interface ManagerService {

	/**
	 * 관리자 목록 개수 조회
	 * @param paramMap
	 * @return
	 */
	public int getManagerCount(ParamCollector paramCollector);
	
	/**
	 * 관리자 목록 조회
	 * @param paramCollector
	 * @return
	 */
	public List<ResultSetMap> getManagerList(ParamCollector paramCollector);
	
	/**
	 * 관리자 아이디 유무 조회
	 * @param paramMap
	 * @return
	 */
	public ResultSetMap getCheckUserName(ParamCollector paramCollector);
	
	/**
	 * 관리자 등록
	 * @param paramMap
	 */
	public void addManager(ParamCollector paramCollector);
	
	/**
	 * 관리자 삭제
	 * @param paramMap
	 */
	public void removeManager(ParamCollector paramCollector);

	/**
	 * 관리자 조회
	 * @param paramMap
	 * @return
	 */
	public ResultSetMap getManagerInfo(ParamCollector paramCollector);
	
	/**
	 * 관리자 수정
	 * @param paramMap
	 */
	public void modifyManager(ParamCollector paramCollector);
	
}
