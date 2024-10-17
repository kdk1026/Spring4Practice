package kr.co.test.rest.cache.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import common.LogDeclare;
import kr.co.test.rest.cache.vo.Member;

@Repository
public class MemberDao extends LogDeclare {
	
	/*
	 * 결과 값이 null 인 경우, 캐싱 적용 안함
	 * unless = "#result != null"
	 * 
	 * 이름이 4자 미만의 parameter로 넘어온 경우에만 캐싱 
	 * condition = "#name.length < 4"
	 */
	
	private void slowQuery(long secondes) {
		try {
			TimeUnit.SECONDS.sleep(secondes);
		} catch (InterruptedException e) {
			throw new IllegalStateException(e);
		}
	}

	@Cacheable(value = "baseCache")
	public List<Member> membersCache() {
		this.slowQuery(2);
		List<Member> list = new ArrayList<Member>();
		Member member = null;

		member = new Member(0, "a@gmail.com", "a");
		list.add(member);
		member = new Member(1, "b@gmail.com", "b");
		list.add(member);
		
		return list;
	}

	@CacheEvict(value = "baseCache", allEntries = true)
	public void remove() {
		logger.debug("List Cache Clear!");
	}
	
	@CachePut(value = "baseCache")
	public void refresh() {
		logger.debug("List Cache Refresh!");
	}

	public Member findByNameNoCache(String name) {
		this.slowQuery(2);
		return new Member(0, name+"@gmail.com", name);
	}
	
	@Cacheable(value = "findMemberCache", key="#name")
	public Member findByNameCache(String name) {
		this.slowQuery(2);
		return new Member(0, name+"@gmail.com", name);
	}
	
	@CacheEvict(value = "findMemberCache", key="#name")
	public void remove(String name) {
		logger.debug("{} 의 Cache Clear!", name);
	}
	
	@CachePut(value = "findMemberCache", key="#name")
	public void refresh(String name) {
		logger.debug("{} 의 Cache Refresh!", name);
	}
	
}
