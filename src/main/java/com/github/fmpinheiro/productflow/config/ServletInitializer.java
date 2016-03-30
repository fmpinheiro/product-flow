package com.github.fmpinheiro.productflow.config;

import com.github.fmpinheiro.productflow.ProductFlowApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ProductFlowApplication.class);
	}

}
