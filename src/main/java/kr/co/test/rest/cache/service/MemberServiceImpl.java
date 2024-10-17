package kr.co.test.rest.cache.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import common.LogDeclare;
import kr.co.test.rest.cache.dao.MemberDao;
import kr.co.test.rest.cache.vo.Member;

@Service
public class MemberServiceImpl extends LogDeclare implements MemberService {

	@Autowired
	private MemberDao memberDao;
	
	@Override
	public List<Member> getMembersCache() {
		return memberDao.membersCache();
	}

	@Override
	public void processRemove() {
		memberDao.remove();
	}
	
	@Override
	public void processRefresh() {
		memberDao.refresh();
	}

	@Override
	public Member getFindByNameNoCache(String name) {
		return memberDao.findByNameNoCache(name);
	}

	@Override
	public Member getFindByNameCache(String name) {
		return memberDao.findByNameCache(name);
	}

	@Override
	public void processRemove(String name) {
		memberDao.remove(name);
	}

	@Override
	public void processRefresh(String name) {
		memberDao.refresh(name);
	}

}