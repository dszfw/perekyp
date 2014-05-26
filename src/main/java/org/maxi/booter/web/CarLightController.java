package org.maxi.booter.web;

import org.maxi.booter.domain.car.Car;
import org.maxi.booter.dto.CarResource;
import org.maxi.booter.dto.CarResourceAssembler;
import org.maxi.booter.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("autos")
public class CarLightController {

	@Autowired
	CarResourceAssembler carAssembler;
	@Autowired
	CarRepository carRepo;

	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<PagedResources<CarResource>> autos(Pageable pageable, PagedResourcesAssembler<Car> pagedResourcesAssembler) {
		Page<Car> cars = carRepo.findAll(pageable);
		return new ResponseEntity<>(pagedResourcesAssembler.toResource(cars, carAssembler), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<CarResource> auto(@PathVariable("id") Car car) {
		if (car == null)
			return new ResponseEntity<CarResource>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<CarResource>(carAssembler.toResource(car), HttpStatus.OK);
	}

}