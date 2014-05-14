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
import org.maxi.booter.domain.car.Car;
import org.maxi.booter.domain.car.CarDefinition;
import org.maxi.booter.domain.car.EngineType;
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
public class EngineTypeRepositoryTest {

	@Autowired
	EngineTypeRepository etRepo;
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
		EngineType et = etRepo.findOne(1L);
		assertThat(et.getName(), is("бензин"));
	}

	@Test
	public void findAll() {
		ArrayList<EngineType> engineTypes = (ArrayList<EngineType>) etRepo.findAll();
		assertThat(engineTypes, notNullValue());
	}
	
	@Test
	public void create() {
		String etName = "дрова";
		EngineType et = new EngineType();
		et.setName(etName);
		EngineType savedEt = etRepo.save(et);

		assertFalse(savedEt.isNew());
		assertEquals(etName, savedEt.getName());
	}

	@Test(expected = DataIntegrityViolationException.class)
	public void createWithNullName() {
		EngineType et = new EngineType();
		etRepo.save(et);
	}

	@Test
	public void update() {
		EngineType et = etRepo.findOne(1L);
		String newName = "бензин с ослиной мочой";
		et.setName(newName);
		EngineType savedEngineType = etRepo.save(et);

		assertEquals(newName, savedEngineType.getName());
	}

	@Test
	public void getCars() {
		EngineType engineType = etRepo.findOne(1L);
		List<Car> cars = engineType.getCars();
		assertThat(cars, notNullValue());
	}

	@Test
	public void getSubscriptions() {
		EngineType engineType = etRepo.findOne(6L);
		List<Subscription> subscriptions = engineType.getSubscriptions();
		assertThat(subscriptions, notNullValue());
	}

	// Cascade operations --------------------------------------------------
	
	@Test
	public void deleteCascadeCar() {
		Long id = 1L;
		EngineType engineType = etRepo.findOne(id);
		List<Car> cars = engineType.getCars();

		etRepo.delete(id);
		engineType = etRepo.findOne(id);
		assertNull(engineType);
		cars.forEach(c -> assertNull(cRepo.findOne(c.getId())));
	}

	@Test
	public void deleteCascadeSubscription() {
		Long id = 1L;
		EngineType engineType = etRepo.findOne(id);
		List<Subscription> subscriptions = engineType.getSubscriptions();

		etRepo.delete(id);
		engineType = etRepo.findOne(id);
		assertNull(engineType);
		subscriptions.forEach(s -> assertNull(sRepo.findOne(s.getId())));
	}

	@Test
	public void updateCascadeCar() {
		Long id = 1L;
		EngineType engineType = etRepo.findOne(id);
		List<Car> cars = engineType.getCars();
		Car car = cars.get(0);
		String newColor = "матовый черный";
		car.setColor(newColor);

		EngineType savedEngineType = etRepo.save(engineType);
		assertEquals(newColor, savedEngineType.getCars().get(0).getColor());
	}

	@Test
	public void updateCascadeSubscription() {
		Long id = 1L;
		EngineType engineType = etRepo.findOne(id);
		Subscription subscription = engineType.getSubscriptions().get(0);
		String newName = "новая подписка";
		subscription.setName(newName);

		EngineType savedEngineType = etRepo.save(engineType);
		assertEquals(newName, savedEngineType.getSubscriptions().get(0).getName());
	}

	@Test
	public void createCascadeCar() {
		Long id = 1L;
		EngineType engineType = etRepo.findOne(id);
		int sizeBefore = engineType.getCars().size();

		Car car = new Car();
		car.setCreatedDate(Calendar.getInstance());
		car.setSiteId("1234567");
		CarDefinition definition = new CarDefinition();
		definition.setModel(cmRepo.findOne(1L));
		definition.setLocation(lRepo.findOne(1l));
		definition.setEngineType(engineType);
		car.setDefinition(definition);

		engineType.getCars().add(car);
		int sizeAfter = etRepo.findOne(id).getCars().size();
		assertThat(sizeBefore, lessThan(sizeAfter));
	}

	@Test
	public void createCascadeSubscription() {
		Long id = 1L;
		EngineType engineType = etRepo.findOne(id);
		int sizeBefore = engineType.getSubscriptions().size();

		// Subscription creation
		Subscription subscription = new Subscription();
		subscription.setUser(uRepo.findOne(1L));
		String name = "новая подписка";
		subscription.setName(name);
		CarDefinition definition = new CarDefinition();
		definition.setLocation(lRepo.findOne(1L));
		definition.setModel(cmRepo.findOne(1L));
		definition.setEngineType(engineType);
		subscription.setDefinition(definition);

		// Save body type and assert result
		engineType.getSubscriptions().add(subscription);
		EngineType savedEngineType = etRepo.findOne(id);
		int sizeAfter = savedEngineType.getSubscriptions().size();
		assertEquals(sizeBefore + 1, sizeAfter);
		assertEquals(name, savedEngineType.getSubscriptions().get(sizeAfter - 1).getName());
	}

}