package com.vali;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.vali.interceptor.AdminInterceptor;
import com.vali.interceptor.AuthorizeInterceptor;
import com.vali.interceptor.ShareInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

	@Autowired
	ShareInterceptor share;
	@Autowired
	AuthorizeInterceptor auth;
	@Autowired
	AdminInterceptor admin;
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(share).addPathPatterns("/**");
	
		
		registry.addInterceptor(auth)
		.addPathPatterns("/account/change","/account/logout","/account/edit","/order/**");
		
		registry.addInterceptor(admin).addPathPatterns("/admin/**");
	}
}

