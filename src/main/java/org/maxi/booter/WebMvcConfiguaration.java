package org.maxi.booter;

import org.maxi.booter.service.CarEventHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class WebMvcConfiguaration extends RepositoryRestMvcConfiguration {

	@Bean
	CarEventHandler carEventHandler() {
		return new CarEventHandler();
	}
	
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.defaultContentType(MediaType.TEXT_HTML)
				.mediaType("html", MediaType.TEXT_HTML)
				.mediaType("json", MediaType.APPLICATION_JSON);
	}
	@Override
	protected void configureJacksonObjectMapper(ObjectMapper objectMapper) {
		objectMapper.findAndRegisterModules();
	}
}
