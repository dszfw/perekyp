package org.maxi.booter.service;

import java.util.List;

import org.maxi.booter.domain.Subscription;
import org.maxi.booter.domain.car.Car;
import org.maxi.booter.repository.subscription.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

@RepositoryEventHandler(Car.class)
public class CarEventHandler {
	
	@Autowired
	private SubscriptionRepository subscriptionRepository;

	@HandleBeforeSave
	public void handleBeforeSave(Car car) {
		System.out.println("qwertyuytrewqwertyuytrewqwertyuytrewerty----HandleBeforeSave");
		
		List<Subscription> subscriptions = subscriptionRepository.findByCarDefinition(car.getDefinition());
		car.getSubscriptions().addAll(subscriptions);
	}
	
	@HandleAfterSave
	public void handleAfterSave(Car car) {
		System.out.println("qwertyuytrewqwertyuytrewqwertyuytrewerty----handleAfterSave");
		
		List<Subscription> subscriptions = subscriptionRepository.findByCarDefinition(car.getDefinition());
		car.getSubscriptions().addAll(subscriptions);
	}
	
	@HandleBeforeCreate
	public void handleBeforeCreate(Car car) {
		System.out.println("qwertyuytrewqwertyuytrewqwertyuytrewerty----HandleBeforeCreate");
		
		List<Subscription> subscriptions = subscriptionRepository.findByCarDefinition(car.getDefinition());
		car.getSubscriptions().addAll(subscriptions);
	}
	
	@HandleAfterCreate
	public void handleAfterCreate(Car car) {
		System.out.println("qwertyuytrewqwertyuytrewqwertyuytrewerty----HandleAfterCreate");
		
		List<Subscription> subscriptions = subscriptionRepository.findByCarDefinition(car.getDefinition());
		car.getSubscriptions().addAll(subscriptions);
	}

}
