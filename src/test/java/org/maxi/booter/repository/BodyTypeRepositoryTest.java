package org.maxi.booter.repository;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.maxi.booter.Application;
import org.maxi.booter.domain.Subscription;
import org.maxi.booter.domain.car.BodyType;
import org.maxi.booter.domain.car.Car;
import org.maxi.booter.domain.car.CarDefinition;
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
	CarRepostitory cRepo;
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
		List<Car> cars = bt.getCars();
		assertThat(cars, notNullValue());
	}

	// Cascade operations --------------------------------------------------
	
	@Test
	public void deleteCascadeCarAndSubscription() {
		BodyType bt = btRepo.findOne(1L);
		List<Car> cars = bt.getCars();
		List<Subscription> subscriptions = bt.getSubscriptions();

		btRepo.delete(1L);
		BodyType bodyType = btRepo.findOne(1L);
		assertNull(bodyType);
		cars.forEach(c -> assertNull(cRepo.findOne(c.getId())));
		subscriptions.forEach(s -> assertNull(sRepo.findOne(s.getId())));
	}

	@Test
	public void updateCascadeCarAndSubscription() {
		BodyType bt = btRepo.findOne(1L);
		List<Car> cars = bt.getCars();
		Car car = cars.get(0);
		String newColor = "матовый";
		car.setColor(newColor);

		Subscription subscription = bt.getSubscriptions().get(0);
		String newName = "новая подписка";
		subscription.setName(newName);

		BodyType savedBodyType = btRepo.save(bt);
		assertEquals(newColor, savedBodyType.getCars().get(0).getColor());
		assertEquals(newName, savedBodyType.getSubscriptions().get(0).getName());
	}

	@Test
	public void createCascadeCar() {
		BodyType bt = btRepo.findOne(1L);
		int sizeBefore = bt.getCars().size();

		Car car = new Car();
		car.setCreatedDate(Calendar.getInstance());
		car.setSiteId("1234567");
		CarDefinition definition = new CarDefinition();
		definition.setModel(cmRepo.findOne(1L));
		definition.setLocation(lRepo.findOne(1l));
		definition.setBodyType(bt);
		car.setDefinition(definition);

		bt.getCars().add(car);
		int sizeAfter = btRepo.findOne(1L).getCars().size();
		assertThat(sizeBefore, lessThan(sizeAfter));
	}

	@Test
	public void createCascadeSubscription() {
		Long btId = 1L;
		BodyType bt = btRepo.findOne(btId);
		int sizeBefore = bt.getSubscriptions().size();

		// Subscription creation
		Subscription subscription = new Subscription();
		subscription.setUser(uRepo.findOne(1L));
		String name = "новая подписка";
		subscription.setName(name);
		CarDefinition definition = new CarDefinition();
		definition.setLocation(lRepo.findOne(1L));
		definition.setModel(cmRepo.findOne(1L));
		definition.setBodyType(bt);
		subscription.setDefinition(definition);

		// Save body type and assert result
		bt.getSubscriptions().add(subscription);
		BodyType sbt = btRepo.findOne(btId);
		int sizeAfter = sbt.getSubscriptions().size();
		assertEquals(sizeBefore + 1, sizeAfter);
		assertEquals(name, sbt.getSubscriptions().get(sizeAfter - 1).getName());
	}

}
