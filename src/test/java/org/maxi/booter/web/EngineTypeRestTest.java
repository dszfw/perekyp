package org.maxi.booter.web;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.maxi.booter.Application;
import org.maxi.booter.domain.car.EngineType;
import org.maxi.booter.repository.EngineTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@ActiveProfiles("testing")
public class EngineTypeRestTest {

	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;
	
	@Autowired
	private EngineTypeRepository engineTypeRepository;
	
	private final String APPLICATION_JSON_UTF8 = "application/json; charset=utf-8";

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

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
		EngineType engineType = engineTypeRepository.findOne(id);
		
		RequestBuilder request = get("/engineTypes/{id}", id).accept(APPLICATION_JSON_UTF8);
		mockMvc.perform(request)
			.andExpect(status().isOk())
			.andExpect(content().contentType(APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$.name", equalTo(engineType.getName())));
	}
	
}
	
