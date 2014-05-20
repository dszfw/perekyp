package org.maxi.booter.web;

import java.util.Calendar;

import org.maxi.booter.domain.Subscription;
import org.maxi.booter.domain.car.Car;
import org.maxi.booter.repository.CarManufacturerRepository;
import org.maxi.booter.repository.CarModelRepository;
import org.maxi.booter.repository.CarRepository;
import org.maxi.booter.repository.LocationRepository;
import org.maxi.booter.repository.subscription.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SimpleController {

	@Autowired
	private CarRepository carRepository;
	@Autowired
	private CarModelRepository carModelRepo;
	@Autowired
	private SubscriptionRepository subscriptionRepository;
	@Autowired
	private LocationRepository locationRepo;
	@Autowired
	private CarManufacturerRepository cmRepo;

//	@RequestMapping(value = "/test1")
//	public @ResponseBody String test1() {
//		
//		Car car = new Car();
//		car.setCreatedDate(Calendar.getInstance());
//		car.setSiteId("1234567");
//		CarDefinition definition = new CarDefinition();
//		definition.setModel(carModelRepo.findOne(1L));
//		definition.setLocation(locationRepo.findOne(1L));
//		car.setDefinition(definition);
//
//		carRepository.save(car);
//		
//		return "Ololo1";
//	}

	@RequestMapping(value = "/test2")
	public @ResponseBody String test2() {
		Subscription subscription = subscriptionRepository.findOne(2L);
		Car car = carRepository.findOne(4L);
		subscription.getCars().add(car);
		subscriptionRepository.save(subscription);
		return "Ololo2";
	}

	@RequestMapping("/test3")
	public @ResponseBody String test3() {
		cmRepo.delete(1L);
		return "Test3";
	}

	@RequestMapping("/test4")
	public @ResponseBody String test4() {
		subscriptionRepository.delete(1L);
		return "Test4";
	}

}