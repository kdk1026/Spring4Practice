package kr.co.test.rest.async.service;

import java.util.concurrent.Future;

import common.util.map.ResultSetMap;

public interface AsyncService {

	public void test1() throws InterruptedException;
	
	public Future<ResultSetMap> test2() throws InterruptedException;
	
	public void other();
	
}
