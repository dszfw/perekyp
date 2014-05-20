package org.maxi.booter.repository;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.maxi.booter.Application;
import org.maxi.booter.domain.Subscription;
import org.maxi.booter.domain.car.Car;
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
public class SubscriptionRepositoryTest {

	@Autowired
	CarRepository carRepo;
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
		assertNotNull(subscriptionRepo.findOne(1L));
	}

	// CRUD operations -----------------------------------------------------
	
	@Test
	public void create() {
		// TODO Need refactoring
		Subscription subscription = new Subscription();
		subscription.setUser(userRepo.findOne(1L));
		String name = "новая подписка";
		subscription.setName(name);
		subscription.setLocation(locationRepo.findOne(1L));
		subscription.setModel(carModelRepo.findOne(1L));

		long sizeBefore = subscriptionRepo.count();
		subscriptionRepo.save(subscription);
		long sizeAfter = subscriptionRepo.count();
		assertFalse(subscription.isNew());
		assertThat(sizeBefore, lessThan(sizeAfter));
		assertEquals(sizeBefore + 1, sizeAfter);
	}

	@Test
	public void update() {
		Long id = 1L;
		String newName = "обновленная подписка";
		Subscription subscription = subscriptionRepo.findOne(id);
		subscription.setName(newName);

		subscriptionRepo.save(subscription);
		assertFalse(subscription.isNew());
		assertEquals(newName, subscription.getName());
	}

	@Test
	public void delete() {
		Long id = 1L;
		long sizeBefore = subscriptionRepo.count();
		subscriptionRepo.delete(id);
		long sizeAfter = subscriptionRepo.count();
		assertNull(subscriptionRepo.findOne(id));
		assertThat(sizeBefore, greaterThan(sizeAfter));
		assertEquals(sizeBefore, sizeAfter + 1);
	}

	// Cascade operations --------------------------------------------------
	
	@Test
	public void createCascadeCar() {
		Long id = 1L;
		Subscription subscription = subscriptionRepo.findOne(id);
		long sizeBefore = subscription.getCars().size();

		Car car = new Car();
		car.setCreatedDate(Calendar.getInstance());
		car.setSiteId("1234567");
		car.setModel(carModelRepo.findOne(1L));
		car.setLocation(locationRepo.findOne(1l));

		subscription.getCars().add(car);
		long sizeAfter = subscription.getCars().size();
		assertThat(sizeBefore, lessThan(sizeAfter));
		assertEquals(sizeBefore + 1, sizeAfter);
	}
	
	@Test
	public void updateCascadeCar() {
		Long id = 1L;
		Subscription subscription = subscriptionRepo.findOne(id);
		Car car = subscription.getCars().iterator().next();
		String newColor = "золотой";
		car.setColor(newColor);
		
		subscriptionRepo.save(subscription);
		assertTrue(subscription.getCars().contains(car));
	}
	
	@Test
	public void noDeleteCascadeCar() {
		Long id = 1L;
		ArrayList<Long> idList = new ArrayList<Long>();
		Set<Car> cars = subscriptionRepo.findOne(id).getCars();
		cars.forEach(car -> idList.add(car.getId()));
		
		subscriptionRepo.delete(id);
		for (Long cid : idList) {
			assertNotNull(carRepo.findOne(cid));
		}
	}
	
	@Test
	public void detachCar() {
		Long id = 1L;
		Subscription subscription = subscriptionRepo.findOne(id);
		Car car = subscription.getCars().iterator().next();
		long sizeBefore = subscription.getCars().size();
		
		subscription.getCars().remove(car);
		long sizeAfter = subscription.getCars().size();
		assertThat(sizeBefore, greaterThan(sizeAfter));
		assertThat(subscription.getCars(), not(contains(car)));
	}
	
	@Test
	public void detachAllCars() {
		Long id = 1L;
		Subscription subscription = subscriptionRepo.findOne(id);
		Set<Car> cars = subscription.getCars();
		assertThat(cars, not(empty()));
		
		subscription.setCars(new HashSet<Car>());;
		assertThat(subscription.getCars(), empty());
		assertThat(cars, not(empty()));
	}
	
	// Query methods -------------------------------------------------------
		
	@Test
	public void findByCarDefinition() {
		Car car = carRepo.findOne(6L);
		List<Subscription> subscriptions = subscriptionRepo.findByCar(car);
		assertTrue(subscriptions.containsAll(car.getSubscriptions()));
	}
		
}
