package kr.co.test.junit.db.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import common.util.map.ParamMap;
import common.util.map.ResultSetMap;

@Mapper
public interface MemberMapper {

	public List<ResultSetMap> selectAll();

	public int insert(ParamMap paramMap);

	public ResultSetMap selectOne(ParamMap paramMap);

	public int update(ParamMap paramMap);

	public int delete(ParamMap paramMap);

}
