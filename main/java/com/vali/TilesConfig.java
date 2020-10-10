package com.vali;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

@Configuration
public class TilesConfig {

	@Bean("viewResolver")
	public ViewResolver getViewResolver() {
		UrlBasedViewResolver url = new UrlBasedViewResolver();
		url.setViewClass(TilesView.class);
		return url;
	}
	@Bean ("tilesConfigurer")
	public TilesConfigurer getTilesConfigurer() {
		TilesConfigurer t = new TilesConfigurer();
		t.setDefinitions("/WEB-INF/tiles.xml");
		return t;
	}
}
