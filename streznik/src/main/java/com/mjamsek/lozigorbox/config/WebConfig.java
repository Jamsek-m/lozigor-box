package com.mjamsek.lozigorbox.config;

import com.mjamsek.lozigorbox.security.interceptorji.ImaVlogoInterceptor;
import com.mjamsek.lozigorbox.security.interceptorji.JeAvtenticiranInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter{

	@Bean
	public BCryptPasswordEncoder passEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public JeAvtenticiranInterceptor addInterceptor() {
		return new JeAvtenticiranInterceptor();
	}
	
	@Bean
	public ImaVlogoInterceptor addInterceptor2() {
		return new ImaVlogoInterceptor();
	}


}
