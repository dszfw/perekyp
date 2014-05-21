package org.maxi.booter.web;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Test;
import org.maxi.booter.domain.car.Car;
import org.maxi.booter.dto.CarDTO;
import org.maxi.booter.repository.subscription.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CarRestTest extends AbstractRestTest<Car> {
	
	@Autowired
	SubscriptionRepository subscriptionRepository;
	
	@Test
	public void getOne() throws Exception {
		Long id = 3L;
		Car car = repository.findOne(id);

		RequestBuilder request = get("/cars/{id}", id).accept(APPLICATION_JSON_UTF8);
		mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().contentType(APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.color", equalTo(car.getColor())))
				.andExpect(jsonPath("$.siteId", equalTo(car.getSiteId())));
	}
	
	@Test
	public void getAll() throws Exception {
		int size = (int) repository.count();
		RequestBuilder request = get("/cars").accept(APPLICATION_JSON_UTF8);
		mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().contentType(APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$._embedded.cars", hasSize(size)));
	}
	
	@Test
	public void deleteOne() throws Exception {
		Long id = 1L;
		RequestBuilder request = delete("/cars/{id}", id);
		mockMvc.perform(request)
				.andExpect(status().isNoContent());
		assertThat(repository.findOne(id), nullValue());
	}
	
	@Test
	public void updateOne() throws Exception {
		Long id = 2L;
		CarDTO dto = new CarDTO();
		dto.siteId = "6xx6ijl96";
		dto.createdDate = "2014-05-10T10:10:10";
		dto.color = "розовый";
		// TODO Look Spring HATEOAS Link class
		dto.model = "http://localhost:8080/carModels/1";
		dto.location = "http://localhost:8080/locations/1";

		// TODO Why is the mapper from super class null?
		ObjectMapper mapper = (ObjectMapper) wac.getBean("objectMapper");

		RequestBuilder request = put("/cars/{id}", id)
				.contentType(MediaType.parseMediaType(APPLICATION_JSON_UTF8))
				.content(mapper.writeValueAsString(dto));
		mockMvc.perform(request)
				.andDo(print())
				.andExpect(status().is2xxSuccessful());
		
		Car car = repository.findOne(id);
		assertThat(id, equalTo(car.getId()));
		assertThat(dto.color, equalTo(car.getColor()));		
		assertThat(parseIdFromLink(dto.model), equalTo(car.getModel().getId()));
	}
	
	// TODO Refactoring. Split this test to two tests
	@Test
	public void createAndEventHandler() throws Exception {
		Long dtoModelId = 1L;
		Long dtoLocationId = 1L;
		CarDTO dto = new CarDTO();
		dto.siteId = "6xx6ijl96";
		dto.color = "розовый";
		dto.createdDate = "2014-05-10T10:10:10";
		dto.model = "http://localhost:8080/carModels/" + dtoModelId;
		dto.location = "http://localhost:8080/locations/" + dtoLocationId;
		
		ObjectMapper mapper = (ObjectMapper) wac.getBean("objectMapper");
		RequestBuilder request = post("/cars/")
				.contentType(MediaType.parseMediaType(APPLICATION_JSON_UTF8))
				.content(mapper.writeValueAsString(dto));
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isCreated())
				.andReturn();

		Car car = repository.findOne(parseIdFromLocationHeader(result));
		assertThat(car.getColor(), equalTo(dto.color));
		assertThat(car.getModel().getId(), equalTo(dtoModelId));
		assertThat(car.getLocation().getId(), equalTo(dtoLocationId));
		assertTrue(subscriptionRepository.findByCar(car).containsAll(car.getSubscriptions()));
	}
	
}
