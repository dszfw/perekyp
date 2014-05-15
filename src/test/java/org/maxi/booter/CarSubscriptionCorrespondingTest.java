package org.maxi.booter;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.maxi.booter.domain.Location;
import org.maxi.booter.domain.Subscription;
import org.maxi.booter.domain.User;
import org.maxi.booter.domain.car.Car;
import org.maxi.booter.domain.car.CarDefinition;
import org.maxi.booter.domain.car.CarModel;
import org.maxi.booter.repository.CarRepostitory;
import org.maxi.booter.repository.UserRepository;
import org.maxi.booter.repository.subscription.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@ActiveProfiles("testing")
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class CarSubscriptionCorrespondingTest {

	@Autowired
	CarRepostitory carRepo;
	@Autowired
	SubscriptionRepository subscriptionRepo;
	@Autowired
	UserRepository userRepo;
	
	@Deprecated
	@Test
	public void corresponding_Old() {
		// Processing unprocessed cars
		List<Car> cars = carRepo.findByProcessed(false);
		
		for (Car car : cars) {
			CarModel model = car.getDefinition().getModel();
			Location location = car.getDefinition().getLocation();
			List<Subscription> subscriptions = subscriptionRepo.findByCarDefinitionParameters(model, location);
			car.setSubscriptions(subscriptions);
		}
		
		// Saving result
		cars = (List<Car>) carRepo.save(cars);
		
		// Asserting
		for (Car car : cars) {
			List<Subscription> carSubscriptions = car.getSubscriptions();
			
			CarModel model = car.getDefinition().getModel();
			Location location = car.getDefinition().getLocation();
			List<Subscription> subscriptions = subscriptionRepo.findByCarDefinitionParameters(model, location);
			
			carSubscriptions.forEach(s -> assertTrue(subscriptions.contains(s)));
		}
	}

	@Test
	@Rollback(false)
	public void corresponding() {
		// Processing unprocessed cars
		List<Car> cars = carRepo.findByProcessed(false);
		
		for (Car car : cars) {
			List<Subscription> subscriptions = subscriptionRepo.findByCarDefinition(car.getDefinition());
			car.setSubscriptions(subscriptions);
		}
		
		// Saving result
		cars = (List<Car>) carRepo.save(cars);
		
		// Asserting
		for (Car car : cars) {
			List<Subscription> subscriptions = subscriptionRepo.findByCarDefinition(car.getDefinition());
			assertTrue(subscriptions.containsAll(car.getSubscriptions()));
		}
	}
	
	@Test
	@Rollback(false)
	public void updateCarSetMoreSubscription() {
		Car car = carRepo.findOne(9L);
		CarModel model = car.getDefinition().getModel();
		Location location = car.getDefinition().getLocation();
		User user = userRepo.findOne(1L);

		List<Subscription> newSubscriptions = new ArrayList<>();
		for (int i = 0; i < 2; i++) {
			Subscription subscription = new Subscription();
			subscription.setUser(user);
			String name = "новая подписка " + i;
			subscription.setName(name);
			CarDefinition definition = new CarDefinition();
			definition.setLocation(location);
			definition.setModel(model);
			subscription.setDefinition(definition);
			newSubscriptions.add(subscription);
		}
		subscriptionRepo.save(newSubscriptions);
		
		List<Subscription> subscriptions = subscriptionRepo.findByCarDefinition(car.getDefinition());
		car.setSubscriptions(subscriptions);
	}
	
	@Test
	@Rollback(false)
	public void updateCarRemoveOneSubscription() {
		Car car = carRepo.findOne(9L);
		car.getSubscriptions().remove(0);
	}
	
	
	
	
	
	
	
	
	
	

	
}
