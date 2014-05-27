package org.maxi.booter.repository;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.maxi.booter.Application;
import org.maxi.booter.domain.Location;
import org.maxi.booter.domain.Subscription;
import org.maxi.booter.domain.User;
import org.maxi.booter.domain.car.Car;
import org.maxi.booter.domain.car.CarModel;
import org.maxi.booter.repository.UserRepository;
import org.maxi.booter.repository.subscription.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@ActiveProfiles("testing")
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class CarSubscriptionRelationshipTest {

	@Autowired
	CarRepository carRepo;
	@Autowired
	SubscriptionRepository subscriptionRepo;
	@Autowired
	UserRepository userRepo;
	
/*
	@Test
	public void corresponding() {
		// Processing unprocessed cars
		List<Car> cars = carRepo.findByProcessed(false);
		
		for (Car car : cars) {
			List<Subscription> subscriptions = subscriptionRepo.findByCar(car);
			car.getSubscriptions().addAll(subscriptions);
		}
		
		// Saving result
		cars = (List<Car>) carRepo.save(cars);
		
		// Asserting
		for (Car car : cars) {
			List<Subscription> subscriptions = subscriptionRepo.findByCar(car);
			assertTrue(subscriptions.containsAll(car.getSubscriptions()));
		}
	}
*/

	@Test
	public void updateCarSetMoreSubscription() {
		Car car = carRepo.findOne(9L);
		CarModel model = car.getModel();
		Location location = car.getLocation();
		User user = userRepo.findOne(1L);

		int addition = 2;
		Set<Subscription> newSubscriptions = new HashSet<Subscription>();
		for (int i = 0; i < addition; i++) {
			// TODO Need to refactoring
			Subscription subscription = new Subscription();
			subscription.setUser(user);
			String name = "новая подписка " + i;
			subscription.setName(name);
			subscription.setLocation(location);
			subscription.setModel(model);
			newSubscriptions.add(subscription);
		}
		subscriptionRepo.save(newSubscriptions);
		
		List<Subscription> subscriptionsFromRepo = subscriptionRepo.findByCar(car);
		Set<Subscription> carSubscriptions = car.getSubscriptions();
		int sizeBefore = carSubscriptions.size();
		carSubscriptions.addAll(subscriptionsFromRepo);
		int sizeAfter = carSubscriptions.size();

		assertEquals(sizeBefore + addition,  sizeAfter);
		assertTrue(carSubscriptions.containsAll(subscriptionsFromRepo));
	}
	
	@Test
	public void updateCarRemoveOneSubscription() {
		Car car = carRepo.findOne(9L);
		int sizeBefore = car.getSubscriptions().size();
		
		Subscription subscription = subscriptionRepo.findOne(9L);
		car.getSubscriptions().remove(subscription);
		assertEquals(sizeBefore - 1, car.getSubscriptions().size());
	}
	
}
