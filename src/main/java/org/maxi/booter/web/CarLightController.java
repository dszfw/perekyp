package org.maxi.booter.web;

import java.time.LocalDate;

import org.maxi.booter.domain.Location;
import org.maxi.booter.domain.car.BodyType;
import org.maxi.booter.domain.car.Car;
import org.maxi.booter.domain.car.CarManufacturer;
import org.maxi.booter.domain.car.CarModel;
import org.maxi.booter.domain.car.Drive;
import org.maxi.booter.domain.car.EngineType;
import org.maxi.booter.domain.car.SellerType;
import org.maxi.booter.domain.car.Transmission;
import org.maxi.booter.dto.CarResource;
import org.maxi.booter.dto.CarResourceAssembler;
import org.maxi.booter.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
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

	// TODO Refactoring, move logic to CarService
	@Autowired
	CarResourceAssembler carAssembler;
	@Autowired
	CarRepository carRepo;

	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<PagedResources<CarResource>> getAutos(Pageable pageable,
			PagedResourcesAssembler<Car> par,
			@RequestParam(required = false) CarManufacturer carManufacturer,
			@RequestParam(required = false) CarModel carModel,
			@RequestParam(required = false) Location location,
			@RequestParam(required = false) BodyType bodyType,
			@RequestParam(required = false) EngineType engineType,
			@RequestParam(required = false) Transmission transmission,
			@RequestParam(required = false) Drive drive,
			@RequestParam(required = false) SellerType sellerType,
			@RequestParam(required = false) Long displacementFrom,
			@RequestParam(required = false) Long displacementTo,
			@RequestParam(required = false) Long mileageFrom,
			@RequestParam(required = false) Long mileageTo,
			@RequestParam(required = false) Long priceFrom,
			@RequestParam(required = false) Long priceTo,
			@RequestParam(required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate yearFrom,
			@RequestParam(required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate yearTo) {
		
		Page<Car> cars = carRepo.findByParams(carManufacturer,carModel, location, bodyType,
				engineType, transmission, drive, sellerType, displacementFrom, displacementTo,
				mileageFrom, mileageTo, priceFrom, priceTo, yearFrom, yearTo, pageable);
		
		return new ResponseEntity<>(par.toResource(cars,carAssembler), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<CarResource> getAuto(@PathVariable("id") Car car) {
		if (car == null)
			return new ResponseEntity<CarResource>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<CarResource>(carAssembler.toResource(car), HttpStatus.OK);
	}

}