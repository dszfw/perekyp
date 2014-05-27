package org.maxi.booter.web;

import org.maxi.booter.domain.Location;
import org.maxi.booter.domain.car.BodyType;
import org.maxi.booter.domain.car.Car;
import org.maxi.booter.domain.car.CarModel;
import org.maxi.booter.domain.car.EngineType;
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
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/autos")
public class CarLightController {

	@Autowired
	CarResourceAssembler carAssembler;
	@Autowired
	CarRepository carRepo;

	// TODO Adding others parameters
	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<PagedResources<CarResource>> getAutos
	(Pageable pageable,
			PagedResourcesAssembler<Car> par,
			@RequestParam(required = false) CarModel model,
			@RequestParam(required = false) Location location,
			@RequestParam(required = false) BodyType bodyType,
			@RequestParam(required = false) EngineType engineType) {
		
		Page<Car> cars = carRepo.findByParams(model, location, bodyType, engineType, pageable);
		
		return new ResponseEntity<>(par.toResource(cars,carAssembler), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<CarResource> getAuto(@PathVariable("id") Car car) {
		if (car == null)
			return new ResponseEntity<CarResource>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<CarResource>(carAssembler.toResource(car), HttpStatus.OK);
	}
	

}