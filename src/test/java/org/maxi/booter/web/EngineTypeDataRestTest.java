package org.maxi.booter.web;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import org.junit.Test;
import org.maxi.booter.domain.car.EngineType;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EngineTypeDataRestTest extends AbstractWebTest<EngineType> {


	@Test
	public void create() throws Exception {
		EngineType engineType = new EngineType();
		engineType.setName("реактивный");
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		
		RequestBuilder request = post("/engineTypes")
				.contentType(MediaType.parseMediaType(APPLICATION_JSON_UTF8))
				.content(mapper.writeValueAsBytes(engineType));
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isCreated())
				.andDo(print())
				.andReturn();
		
		System.out.println(result.getResponse().getHeader("Location"));
	}
	
	@Test
	public void getOne() throws Exception {
		Long id = 3L;
		EngineType engineType = repository.findOne(id);
		
		RequestBuilder request = get("/engineTypes/{id}", id).accept(APPLICATION_JSON_UTF8);
		mockMvc.perform(request)
			.andExpect(status().isOk())
			.andExpect(content().contentType(APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$.name", equalTo(engineType.getName())));
	}
	
}
	
