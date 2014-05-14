package org.maxi.booter;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.maxi.booter.domain.Location;
import org.maxi.booter.domain.Subscription;
import org.maxi.booter.domain.car.Car;
import org.maxi.booter.domain.car.CarModel;
import org.maxi.booter.repository.CarRepostitory;
import org.maxi.booter.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@ActiveProfiles("testing")
public class CarSubscriptionCorrespondingTest {

	@Autowired
	CarRepostitory carRepo;
	@Autowired
	SubscriptionRepository subscriptionRepo;
	
	@Test
	public void corresponding() {
	
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
	
}
