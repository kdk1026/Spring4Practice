package config.spring.app;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@EnableAsync
public class TaskConfig {
	
	@Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(50);
        executor.setQueueCapacity(10);
        executor.setKeepAliveSeconds(120);
    
        /*
         * AbortPolicy()			: Exception Throw (Default)
         * CallerRunsPolicy()		: 해당 Application이 과부하 상태일 경우 TaskExecutor에 의해서가 아닌 Thread에서 직접 Task를 실행시킬 수 있게 한다
         * DiscardPolicy()			: 해당 Application이 과부하 상태일 경우 현재 Task의 실행을 Skip
         * DiscardOldestPolicy()	: 해당 Application이 과부하 상태일 경우 Queue의 Head에 있는 Task의 실행을 Skip
         */	
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }
	
}
