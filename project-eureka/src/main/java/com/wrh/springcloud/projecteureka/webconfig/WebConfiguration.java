package com.wrh.springcloud.projecteureka.webconfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@PropertySource("test.properties")
public class WebConfiguration extends WebSecurityConfigurerAdapter {

	private Logger logger = LoggerFactory.getLogger(WebConfiguration.class);
	@Value("${server.port}")
	private int value;
	@Value("${name}")
	private String name;
    @Override
    protected  void configure(HttpSecurity http) throws Exception{
    	logger.info(value+"================================="+name);
        http.csrf().disable();
        super.configure(http);
    }
}
