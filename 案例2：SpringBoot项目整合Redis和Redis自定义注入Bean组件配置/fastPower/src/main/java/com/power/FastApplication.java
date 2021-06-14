package com.power;

import com.power.datasources.DynamicDataSourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;


@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@Import({DynamicDataSourceConfig.class})
public class FastApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {

		SpringApplication.run(FastApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(FastApplication.class);
	}
}
