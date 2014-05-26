package org.maxi.booter.web;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.maxi.booter.domain.car.Car;
import org.maxi.booter.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.RequestBuilder;


public class CarLightControllerTest extends AbstractWebTest<Car>{
	
	@Autowired
	CarRepository pagingRepository;
	
	@Test
	public void getAllPageable() throws Exception {
		int pageNumber = 0;
		int size = 20;
		Page<Car> page = pagingRepository.findAll(new PageRequest(pageNumber, size));
		RequestBuilder request = get("/autos?page={page}&size={size}", page, size).accept(APPLICATION_JSON_UTF8);
		mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().contentType(APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.page.number", equalTo(page.getNumber())))
				.andExpect(jsonPath("$._embedded.carResources", hasSize(page.getNumberOfElements())))
				.andExpect(jsonPath("$.page.totalPages", equalTo(page.getTotalPages())))
				.andExpect(jsonPath("$.page.totalElements", equalTo((int) page.getTotalElements())));
	}
	
	@Test
	public void getOne() throws Exception {
		Long id = 1L;
		Car car = pagingRepository.findOne(id);
		RequestBuilder request = get("/autos/{id}", id).accept(APPLICATION_JSON_UTF8);
		mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().contentType(APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.color", equalTo(car.getColor())))
				.andExpect(jsonPath("$.manufacturer", equalTo(car.getModel().getManufacturer().getName())))
				.andExpect(jsonPath("$.location", equalTo(car.getLocation().getName())))
				.andExpect(jsonPath("$.year[0]", equalTo(car.getYear().getYear())));
		
	}
	
}
