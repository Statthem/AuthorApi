package com.statthem.authorapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.statthem.authorapi.configuration.DataSourceConfiguration;
import com.statthem.authorapi.configuration.SecurityConfiguration;

@SpringBootApplication
@ComponentScan(basePackages="com.statthem.authorapi")
public class AuthorapiApplication  extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(AuthorapiApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AuthorapiApplication.class);
	}

	
}	
