package org.maxi.booter.web;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Calendar;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.maxi.booter.Application;
import org.maxi.booter.domain.car.Car;
import org.maxi.booter.domain.car.CarDefinition;
import org.maxi.booter.repository.CarModelRepository;
import org.maxi.booter.repository.CarRepostitory;
import org.maxi.booter.repository.LocationRepository;
import org.maxi.booter.repository.subscription.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@ActiveProfiles("testing")
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class CarRestTest {

	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;
	
	@Autowired
	CarRepostitory carRepo;
	@Autowired
	SubscriptionRepository subscriptionRepo;
	@Autowired
	CarModelRepository carModelRepo;
	@Autowired
	LocationRepository locationRepo;

	private final String APPLICATION_JSON_UTF8 = "application/json; charset=utf-8";

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void getOne() throws Exception {
		Long id = 3L;
		Car car = carRepo.findOne(id);

		RequestBuilder request = get("/cars/{id}", id).accept(APPLICATION_JSON_UTF8);
		mockMvc.perform(request)
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentType(APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$.color", equalTo(car.getColor())));
	}

	
	@Test
	@Rollback(false)
	public void createEventHandler() throws Exception {
		String json = "{" +
  "\"siteId\": \"овщ\"," +
  "\"createdDate\": \"2014-05-12\"," +
  "\"definition\" : {" +
    "\"model\": {" +
      "\"id\": \"1\"" +
    "},"+
    "\"location\": {" +
      "\"id\": \"1\"" +
    "}"+
  "}"+
"}";
		// TODO need refactoring
//		Car car = new Car();
//		car.setCreatedDate(Calendar.getInstance());
//		car.setSiteId("1234567");
//		car.setColor("благородный");
//		CarDefinition definition = new CarDefinition();
//		definition.setModel(carModelRepo.findOne(1L));
//		definition.setLocation(locationRepo.findOne(1l));
//		car.setDefinition(definition);

//		ObjectMapper mapper = new ObjectMapper();
//		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		
		System.out.println(json);
		
		RequestBuilder request = post("/cars/")
				.contentType(MediaType.parseMediaType(APPLICATION_JSON_UTF8))
				.content(json);
		mockMvc.perform(request)
			.andDo(print())
			.andExpect(status().isCreated());
	}
	
	@Test
	public void mapper() throws JsonProcessingException {
		Car car = carRepo.findOne(1L);
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(car));
	}
	
}
