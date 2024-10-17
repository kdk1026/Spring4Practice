package config.spring.mvc;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;

public class TilesConfig {
	
	@Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer tc = new TilesConfigurer();
		tc.setDefinitions("/WEB-INF/tiles.xml");
		return tc;
	}
	
}
