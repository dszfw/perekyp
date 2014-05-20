package org.maxi.booter.repository;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.maxi.booter.Application;
import org.maxi.booter.domain.Subscription;
import org.maxi.booter.domain.car.BodyType;
import org.maxi.booter.domain.car.Car;
import org.maxi.booter.repository.subscription.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@ActiveProfiles("testing")
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class BodyTypeRepositoryTest {

	@Autowired
	BodyTypeRepository btRepo;
	@Autowired
	CarRepository cRepo;
	@Autowired
	SubscriptionRepository sRepo;
	@Autowired
	CarModelRepository cmRepo;
	@Autowired
	LocationRepository lRepo;
	@Autowired
	UserRepository uRepo;

	@Test
	public void find() {
		BodyType bodyType = btRepo.findOne(1L);
		assertThat(bodyType.getName(), is("седан"));
	}

	@Test
	public void create() {
		String btName = "лифтбек";
		BodyType bt = new BodyType();
		bt.setName(btName);
		BodyType sbt = btRepo.save(bt);

		assertThat(sbt, notNullValue());
		assertFalse(sbt.isNew());
		assertEquals(btName, sbt.getName());
	}

	@Test(expected = DataIntegrityViolationException.class)
	public void createWithNullName() {
		BodyType bodyType = new BodyType();
		btRepo.save(bodyType);
	}

	@Test
	public void update() {
		BodyType bodyType = btRepo.findOne(1L);
		String newName = "ласточка";
		bodyType.setName(newName);
		BodyType savedBodyType = btRepo.save(bodyType);

		assertEquals(newName, savedBodyType.getName());
	}

	@Test
	public void findAll() {
		ArrayList<BodyType> bodyTypes = (ArrayList<BodyType>) btRepo.findAll();

		assertThat(bodyTypes, not(empty()));
	}

	@Test
	public void getCars() {
		BodyType bt = btRepo.findOne(1L);
		Set<Car> cars = bt.getCars();
		assertThat(cars, notNullValue());
	}

	// Cascade operations --------------------------------------------------
	
	@Test
	public void deleteCascadeCarAndSubscription() {
		BodyType bt = btRepo.findOne(1L);
		Set<Car> cars = bt.getCars();
		Set<Subscription> subscriptions = bt.getSubscriptions();

		btRepo.delete(1L);
		BodyType bodyType = btRepo.findOne(1L);
		assertNull(bodyType);
		cars.forEach(c -> assertNull(cRepo.findOne(c.getId())));
		subscriptions.forEach(s -> assertNull(sRepo.findOne(s.getId())));
	}

	@Test
	public void updateCascadeCarAndSubscription() {
		BodyType bt = btRepo.findOne(1L);
		Set<Car> cars = bt.getCars();
		Car car = cars.iterator().next();
		String newColor = "матовый";
		car.setColor(newColor);

		Subscription subscription = bt.getSubscriptions().iterator().next();
		String newName = "новая подписка";
		subscription.setName(newName);

		BodyType savedBodyType = btRepo.save(bt);
		assertTrue(savedBodyType.getCars().contains(car));
		assertTrue(savedBodyType.getSubscriptions().contains(subscription));
	}

	@Test
	public void createCascadeCar() {
		BodyType bt = btRepo.findOne(1L);
		int sizeBefore = bt.getCars().size();

		// TODO Refactoring
		Car car = new Car();
		car.setCreatedDate(LocalDateTime.now());
		car.setSiteId("1234567");
		car.setModel(cmRepo.findOne(1L));
		car.setLocation(lRepo.findOne(1l));
		car.setBodyType(bt);

		bt.getCars().add(car);
		int sizeAfter = btRepo.findOne(1L).getCars().size();
		assertThat(sizeBefore, lessThan(sizeAfter));
	}

	@Test
	public void createCascadeSubscription() {
		Long btId = 1L;
		BodyType bt = btRepo.findOne(btId);
		int sizeBefore = bt.getSubscriptions().size();

		// TODO Refactoring
		// Subscription creation
		Subscription subscription = new Subscription();
		subscription.setUser(uRepo.findOne(1L));
		String name = "новая подписка";
		subscription.setName(name);
		subscription.setLocation(lRepo.findOne(1L));
		subscription.setModel(cmRepo.findOne(1L));
		subscription.setBodyType(bt);

		// Save body type and assert result
		bt.getSubscriptions().add(subscription);
		BodyType sbt = btRepo.findOne(btId);
		int sizeAfter = sbt.getSubscriptions().size();
		assertEquals(sizeBefore + 1, sizeAfter);
		assertTrue(sbt.getSubscriptions().contains(subscription));
	}

}
