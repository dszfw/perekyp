package org.maxi.booter.repository;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.maxi.booter.Application;
import org.maxi.booter.domain.Subscription;
import org.maxi.booter.domain.car.Car;
import org.maxi.booter.domain.car.CarDefinition;
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
public class CarRepositoryTest {

	@Autowired
	CarRepostitory carRepo;
	@Autowired
	SubscriptionRepository subscriptionRepo;
	@Autowired
	CarModelRepository carModelRepo;
	@Autowired
	LocationRepository locationRepo;
	@Autowired
	UserRepository userRepo;

	@Test
	public void find() {
		assertNotNull(carRepo.findOne(1L));
	}

	@Test
	public void create() {
		Car car = new Car();
		car.setCreatedDate(Calendar.getInstance());
		car.setSiteId("1234567");
		CarDefinition definition = new CarDefinition();
		definition.setModel(carModelRepo.findOne(1L));
		definition.setLocation(locationRepo.findOne(1l));
		car.setDefinition(definition);

		long sizeBefore = carRepo.count();
		Car savedCar = carRepo.save(car);
		long sizeAfter = carRepo.count();
		assertFalse(savedCar.isNew());
		assertThat(sizeBefore, lessThan(sizeAfter));
		assertEquals(sizeBefore + 1, sizeAfter);
	}

	@Test
	public void update() {
		Long id = 1L;
		String newColor = "золотой";
		Car car = carRepo.findOne(id);
		car.setColor(newColor);

		Car savedCar = carRepo.save(car);
		assertFalse(savedCar.isNew());
		assertEquals(newColor, savedCar.getColor());
	}

	@Test
	public void delete() {
		Long id = 1L;
		long sizeBefore = carRepo.count();
		carRepo.delete(id);
		long sizeAfter = carRepo.count();
		assertNull(carRepo.findOne(id));
		assertThat(sizeBefore, greaterThan(sizeAfter));
		assertEquals(sizeBefore, sizeAfter + 1);
	}
	
	@Test
	public void getSubscriptions() {
		Car car = carRepo.findOne(1L);
		List<Subscription> subscriptions = car.getSubscriptions();
		
		// TODO should be delete
		System.out.println(subscriptions);
		
		assertThat(subscriptions, notNullValue());
		assertThat(subscriptions, not(empty()));
	}

	// Cascade operations --------------------------------------------------
	
	@Test
	public void createCascadeSubscription() {
		Long id = 1L;
		Car car = carRepo.findOne(id);
		int sizeBefore = car.getSubscriptions().size();

		Subscription subscription = new Subscription();
		subscription.setUser(userRepo.findOne(1L));
		String name = "новая подписка";
		subscription.setName(name);
		CarDefinition definition = new CarDefinition();
		definition.setLocation(locationRepo.findOne(1L));
		definition.setModel(carModelRepo.findOne(1L));
		subscription.setDefinition(definition);

		car.getSubscriptions().add(subscription);
		Car savedCar = carRepo.findOne(id);
		int sizeAfter = savedCar.getSubscriptions().size();
		assertThat(sizeBefore, lessThan(sizeAfter));
		assertEquals(sizeBefore + 1, sizeAfter);
		assertEquals(name, savedCar.getSubscriptions().get(sizeAfter - 1).getName());
	}
	
	@Test
	public void updateCascadeSubscription() {
		Long id = 1L;
		Car car = carRepo.findOne(id);
		Subscription subscription = car.getSubscriptions().get(0);
		String newName = "обновленная подписка";
		subscription.setName(newName);
		
		Car savedCar = carRepo.save(car);
		assertEquals(newName, savedCar.getSubscriptions().get(0).getName());
	}
	
	@Test
	public void noDeleteCascadeSubscription() {
		Long id = 1L;
		ArrayList<Long> idList = new ArrayList<Long>();
		List<Subscription> subscriptions = carRepo.findOne(id).getSubscriptions();
		subscriptions.forEach(s -> idList.add(s.getId()));
		
		carRepo.delete(id);
		for (Long sid : idList) {
			assertNotNull(subscriptionRepo.findOne(sid));
		}
	}
	
	@Test
	public void detachSubscription() {
		Long id = 1L;
		Car car = carRepo.findOne(id);
		Subscription subscription = car.getSubscriptions().get(0);
		int sizeBefore = car.getSubscriptions().size();
		
		car.getSubscriptions().remove(subscription);
		Car savedCar = carRepo.findOne(id);
		int sizeAfter = savedCar.getSubscriptions().size();
		assertThat(sizeBefore, greaterThan(sizeAfter));
		assertThat(savedCar.getSubscriptions(), not(contains(subscription)));
	}
	
	@Test
	public void detachAllSubscriptions() {
		Long id = 1L;
		Car car = carRepo.findOne(id);
		List<Subscription> subscriptions = car.getSubscriptions();
		assertThat(subscriptions, not(empty()));
		
		car.setSubscriptions(new ArrayList<Subscription>());;
		Car savedCar = carRepo.findOne(id);
		assertThat(savedCar.getSubscriptions(), empty());
		assertThat(subscriptions, not(empty()));
	}
	
	// Query methods -------------------------------------------------------

	@Test
	public void findByProcessed() {
		boolean processed = false;
		List<Car> cars = carRepo.findByProcessed(processed);
		cars.forEach(c -> assertFalse(c.isProcessed()));

		processed = true;
		cars = carRepo.findByProcessed(processed);
		cars.forEach(c -> assertTrue(c.isProcessed()));
	}
	
}
