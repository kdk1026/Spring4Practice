package config.spring;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;

import config.spring.app.DataSourceConfig;
import config.spring.app.EhCacheConfig;
import config.spring.app.MyBatisConfig;
import config.spring.app.TaskConfig;
import kr.co.test.common.spring.support.DatabaseMessageSource;
import kr.co.test.page.locale.service.LocaleServiceImpl;

@Configuration
@ComponentScan(basePackages = {"kr.co.test"},
	excludeFilters = {
		@Filter(value = Controller.class, type = FilterType.ANNOTATION),
		@Filter(value = ControllerAdvice.class, type = FilterType.ANNOTATION)
	}
)
@Import({
	DataSourceConfig.class, MyBatisConfig.class, EhCacheConfig.class, TaskConfig.class
})
public class AppConfig {

	@Bean
	public PropertiesFactoryBean env() {
		PropertiesFactoryBean properties = new PropertiesFactoryBean();
		properties.setLocation(new ClassPathResource("properties/env.properties"));
		return properties;
	}
	@Bean
	public PropertiesFactoryBean file() {
		PropertiesFactoryBean properties = new PropertiesFactoryBean();
		properties.setLocation(new ClassPathResource("properties/file.properties"));
		return properties;
	}
	@Bean
	public PropertiesFactoryBean jwt() {
		PropertiesFactoryBean properties = new PropertiesFactoryBean();
		properties.setLocation(new ClassPathResource("properties/jwt.properties"));
		return properties;
	}

	@Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:locale/messages");
        messageSource.setDefaultEncoding(StandardCharsets.UTF_8.toString());
        messageSource.setCacheSeconds(0);
        messageSource.setFallbackToSystemLocale(false);
        return messageSource;
    }
	
	@Bean
	public DatabaseMessageSource databaseMessageSource() {
		DatabaseMessageSource databaseMessageSource = new DatabaseMessageSource();
		databaseMessageSource.setMessages(new LocaleServiceImpl());
		return databaseMessageSource;
	}

}
