package config.spring.mvc;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

public class TemplateEngineConfig {

	// XXX : deprecated
	/*
	@Bean
	public VelocityConfigurer velocityConfigurer() {
		VelocityConfigurer vc = new VelocityConfigurer();
		vc.setResourceLoaderPath("/WEB-INF/views/vm/");
		return vc;
	}
	*/
	
	@Bean
	public FreeMarkerConfigurer freeMarkerConfigurer() {
		FreeMarkerConfigurer fmc = new FreeMarkerConfigurer();
		fmc.setTemplateLoaderPaths("/WEB-INF/views/ftl/");
		return fmc;
	}
	
	@Bean
	public SpringResourceTemplateResolver templateResolver() {
		SpringResourceTemplateResolver srtr = new SpringResourceTemplateResolver();
		srtr.setPrefix("/WEB-INF/views/");
		srtr.setSuffix(".html");
		srtr.setTemplateMode("HTML5");
		srtr.setCacheable(false);
		return srtr;
	}
	@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine ste = new SpringTemplateEngine();
		ste.setTemplateResolver( templateResolver() );
		return ste;
	}
	@Bean
	public ThymeleafViewResolver thymeleafViewResolver() {
		ThymeleafViewResolver tvr = new ThymeleafViewResolver();
		tvr.setTemplateEngine( templateEngine() );
		tvr.setViewNames( new String[] {"*thymeleaf/*"} );
		tvr.setOrder(5);
		return tvr;
	}
}
