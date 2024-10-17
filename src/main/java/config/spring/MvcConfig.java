package config.spring;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.stereotype.Controller;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.accept.PathExtensionContentNegotiationStrategy;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.xml.MarshallingView;

import common.spring.resolver.ParamMapArgResolver;
import config.spring.mvc.TemplateEngineConfig;
import config.spring.mvc.TilesConfig;
import config.spring.mvc.ViewConfig;
import kr.co.test.common.spring.util.Jaxb2MarshallerCustom;

@EnableWebMvc
@ComponentScan(basePackages = {"kr.co.test"},
	useDefaultFilters = false,
	includeFilters = {
		@Filter(value = Controller.class, type = FilterType.ANNOTATION),
		@Filter(value = ControllerAdvice.class, type = FilterType.ANNOTATION)
	}
)
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Import({
	ViewConfig.class, TilesConfig.class, TemplateEngineConfig.class
})
public class MvcConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(new ParamMapArgResolver());
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		StringHttpMessageConverter stringConverter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
		List<MediaType> stringMediaTypes = new ArrayList<>();
		stringMediaTypes.add(MediaType.TEXT_HTML);
		stringConverter.setSupportedMediaTypes(stringMediaTypes);
		
		converters.add(new MappingJackson2HttpMessageConverter());
		
		MarshallingHttpMessageConverter marshallingConverter = new MarshallingHttpMessageConverter();
		marshallingConverter.setMarshaller( xStreamMarshaller() );
		marshallingConverter.setUnmarshaller( xStreamMarshaller() );
	}
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.beanName();
		registry.tiles();
//		registry.velocity();
		registry.freeMarker();
		registry.jsp("/WEB-INF/jsp/", ".jsp");
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	
	@Bean
	public MultipartResolver multipartResolver() {
		/*
		 *	CommonsMultipartResolver (Commons FileUpload)
		 *	StandardServletMultipartResolver (Servlet 3.0+ API)
		 */
		return new StandardServletMultipartResolver();
	}
	
	/**
	 * HttpMessageConverter 참조 : Jaxb 의 @XmlRootElement 무시, classpath 로 표기
	 * @return
	 */
	@Bean
	public XStreamMarshaller xStreamMarshaller() {
		XStreamMarshaller xStreamMarshaller = new XStreamMarshaller();
		xStreamMarshaller.setAutodetectAnnotations(true);
		return xStreamMarshaller;
	}
	
	/**
	 * HttpMessageConverter 참조 : Xstream @XStreamAlias 인식 불가
	 * @return
	 */
	@Bean
	public Jaxb2Marshaller jaxbMarshaller() {
		Jaxb2MarshallerCustom jaxb2Custom = new Jaxb2MarshallerCustom();
		return jaxb2Custom.jaxb2Marshaller("kr.co.test");
	}

	@Bean
	public SessionLocaleResolver localeResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(new Locale("ko"));
		return new SessionLocaleResolver();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");
		
		registry.addInterceptor(localeChangeInterceptor).addPathPatterns("/**");
	}
	
	@Bean
	public MarshallingView xstreamView() {
		MarshallingView xstreamView = new MarshallingView();
		xstreamView.setMarshaller( xStreamMarshaller() );
		xstreamView.setModelKey("xmlData");
		return xstreamView;
	}
	
	@Bean
	public MarshallingView jaxbView() {
		MarshallingView jaxbView = new MarshallingView();
		jaxbView.setMarshaller( jaxbMarshaller() );
		jaxbView.setModelKey("xmlData");
		return jaxbView;
	}
	
	@Bean
	public ContentNegotiatingViewResolver contentNegotiatingViewResolver() {
		ContentNegotiatingViewResolver cnv = new ContentNegotiatingViewResolver();
		
		cnv.setContentNegotiationManager( this.contentNegotiationManager() );
		cnv.setDefaultViews(this.defaultViews());
		cnv.setOrder(1);
		return cnv;
	}
	
	@Bean
	public ContentNegotiationManager contentNegotiationManager() {
		Map<String, MediaType> mediaTypes = new HashMap<>();
		mediaTypes.put("json", MediaType.APPLICATION_JSON);
		mediaTypes.put("xml", MediaType.APPLICATION_XML);
		
		ContentNegotiationStrategy cns = new PathExtensionContentNegotiationStrategy(mediaTypes);
		return new ContentNegotiationManager(cns);
	}
	
	private List<View> defaultViews() {
		List<View> defaultViews = new ArrayList<>();
		defaultViews.add(new MappingJackson2JsonView());
		defaultViews.add(new MarshallingView(xStreamMarshaller()));
		return defaultViews;
	}

}
