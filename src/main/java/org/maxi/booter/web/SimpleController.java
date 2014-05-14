package org.maxi.booter.web;

import org.maxi.booter.domain.Subscription;
import org.maxi.booter.domain.car.Car;
import org.maxi.booter.repository.CarManufacturerRepository;
import org.maxi.booter.repository.CarRepostitory;
import org.maxi.booter.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SimpleController {

	@Autowired
	private CarRepostitory carRepository;

	@Autowired
	private SubscriptionRepository subscriptionRepository;

	@Autowired
	private CarManufacturerRepository cmRepo;

	@RequestMapping(value = "/test1")
	public @ResponseBody String test1() {
		Subscription subscription = subscriptionRepository.findOne(1L);
		Car car = carRepository.findOne(4L);
		car.getSubscriptions().add(subscription);
		carRepository.save(car);
		return "Ololo1";
	}

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