package kr.co.test.rest.async.service;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import common.LogDeclare;
import common.util.map.ResultSetMap;

@Service
public class AsyncServiceImpl extends LogDeclare implements AsyncService {

	@Async
	@Override
	public void test1() {
		int n1 = 3;
		int n2 = 4;
		
		this.slowQuery(2);
		logger.debug("test1() start/end : 3+4 = {}", (n1 + n2));
	}

	@Async
	@Override
	public Future<ResultSetMap> test2() {
		int n1 = 3;
		int n2 = 4;
		
		ResultSetMap resMap = new ResultSetMap();
		resMap.put("sum", (n1 + n2));
		
		this.slowQuery(2);
		logger.debug("test2() start/end : 3+4 = {}", resMap);
		
		return new AsyncResult<ResultSetMap>(resMap);
	}

	@Override
	public void other() {
		int n1 = 4;
		int n2 = 3;
		
		logger.debug("other() start/end : 4-3 = {}", (n1 - n2));
	}
	
	private void slowQuery(long secondes) {
		try {
			TimeUnit.SECONDS.sleep(secondes);
		} catch (InterruptedException e) {
			throw new IllegalStateException(e);
		}
	}
	
}
