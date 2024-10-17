package config;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

public class SiteMeshFilter extends ConfigurableSiteMeshFilter {

	@Override
	protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
		builder.addDecoratorPath("/layout/sitemesh", "/WEB-INF/jsp/template/sample/sample_decorator.jsp");
		
		builder.addDecoratorPath("/admin/*", "/WEB-INF/jsp/template/admin/base_decorator.jsp")
		.addExcludedPath("/admin/login");
	}
	
}
