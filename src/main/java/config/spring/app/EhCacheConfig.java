package config.spring.app;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

@EnableCaching
public class EhCacheConfig {
	
    @Bean
    public CacheManager cacheManager() {
    	EhCacheCacheManager cacheManager = new EhCacheCacheManager();
    	cacheManager.setCacheManager(this.ehCacheCacheManager().getObject());
    	return cacheManager;
    }
    
    @Bean
    public EhCacheManagerFactoryBean ehCacheCacheManager() {
    	EhCacheManagerFactoryBean factory = new EhCacheManagerFactoryBean();
    	factory.setConfigLocation(new ClassPathResource("ehcache.xml"));
    	factory.setShared(true);
    	return factory;
    }
	
}
