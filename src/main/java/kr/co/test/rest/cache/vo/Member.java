package kr.co.test.rest.cache.vo;

import common.BaseObject;

public class Member extends BaseObject {

	private static final long serialVersionUID = 1L;
	
	private long idx;
	private String meail;
	private String name;
	
	public Member(long idx, String meail, String name) {
		this.idx = idx;
		this.meail = meail;
		this.name = name;
	}

	public long getIdx() {
		return idx;
	}

	public void setIdx(long idx) {
		this.idx = idx;
	}

	public String getMeail() {
		return meail;
	}

	public void setMeail(String meail) {
		this.meail = meail;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
