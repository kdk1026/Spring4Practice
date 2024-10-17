package kr.co.test.rest.cache.controller;

import java.util.List;

import org.apache.commons.lang3.time.StopWatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import common.LogDeclare;
import kr.co.test.rest.cache.service.MemberService;
import kr.co.test.rest.cache.vo.Member;

@RestController
@RequestMapping("/cache")
public class MemberController extends LogDeclare {

	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value = "/members/cache", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Member> getCacheMembers() {
		StopWatch stopWatch = new StopWatch();
		stopWatch.reset();
		stopWatch.start();
		
		List<Member> members = memberService.getMembersCache();
		
		stopWatch.stop();
		logger.debug("List Cache 수행시간 : {}", stopWatch.toString());
		
		return members;
	}
	
	@RequestMapping(value = "/members/remove", produces = MediaType.TEXT_PLAIN_VALUE)
	public String remove() {
		memberService.processRemove();
		
		return "Clear List Cache";
	}
	
	@RequestMapping(value = "/members/refresh", produces = MediaType.TEXT_PLAIN_VALUE)
	public String refresh() {
		memberService.processRemove();
		
		return "Refresh List Cache";
	}
	
	@RequestMapping(value = "/member/nocache/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Member getNoCacheMember(@PathVariable String name) {
		StopWatch stopWatch = new StopWatch();
		stopWatch.reset();
		stopWatch.start();
		
		Member member = memberService.getFindByNameNoCache(name);
		
		stopWatch.stop();
		logger.debug("{} 의 NoCache 수행시간 : {}", name, stopWatch.toString());
		
		return member;
	}
	
	@RequestMapping(value = "/member/cache/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Member getCacheMember(@PathVariable String name) {
		StopWatch stopWatch = new StopWatch();
		stopWatch.reset();
		stopWatch.start();
		
		Member member = memberService.getFindByNameCache(name);
		
		stopWatch.stop();
		logger.debug("{} 의 Cache 수행시간 : {}", name, stopWatch.toString());
		
		return member;
	}
	
	@RequestMapping(value = "/member/remove/{name}", produces = MediaType.TEXT_PLAIN_VALUE)
	public String remove(@PathVariable String name) {
		memberService.processRemove(name);
		
		return "Clear Key Cache";
	}
	
	@RequestMapping(value = "/members/refresh/{name}", produces = MediaType.TEXT_PLAIN_VALUE)
	public String refresh(@PathVariable String name) {
		memberService.processRemove();
		
		return "Refresh Key Cache";
	}
	
}
