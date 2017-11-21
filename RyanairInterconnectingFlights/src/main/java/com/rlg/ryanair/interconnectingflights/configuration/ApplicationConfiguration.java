package com.rlg.ryanair.interconnectingflights.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.rlg.ryanair.interconnectingflights")
public class ApplicationConfiguration {
	

}