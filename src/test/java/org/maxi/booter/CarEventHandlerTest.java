package org.maxi.booter;

import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.maxi.booter.domain.Subscription;
import org.maxi.booter.domain.car.Car;
import org.maxi.booter.domain.car.CarDefinition;
import org.maxi.booter.repository.CarModelRepository;
import org.maxi.booter.repository.CarRepostitory;
import org.maxi.booter.repository.LocationRepository;
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
public class CarEventHandlerTest {

	@Autowired
	CarRepostitory carRepo;
	@Autowired
	SubscriptionRepository subscriptionRepo;
	@Autowired
	CarModelRepository carModelRepo;
	@Autowired
	LocationRepository locationRepo;
	
	@Test
	@Rollback(false)
	public void create() {
		// TODO need refactoring
		Car car = new Car();
		car.setCreatedDate(Calendar.getInstance());
		car.setSiteId("1234567");
		CarDefinition definition = new CarDefinition();
		definition.setModel(carModelRepo.findOne(1L));
		definition.setLocation(locationRepo.findOne(1l));
		car.setDefinition(definition);

		carRepo.save(car);
		
		System.out.println(car.getSubscriptions());
		
		List<Subscription> subscriptionsFromRepo = subscriptionRepo.findByCarDefinition(car.getDefinition());
		assertTrue(car.getSubscriptions().containsAll(subscriptionsFromRepo));
	}
	
}
