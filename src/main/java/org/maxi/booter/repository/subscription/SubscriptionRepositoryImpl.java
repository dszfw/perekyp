package org.maxi.booter.repository.subscription;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.maxi.booter.domain.Subscription;
import org.maxi.booter.domain.car.Car;

public class SubscriptionRepositoryImpl implements SubscriptionRepositoryCustom {

	private static final String FIND_BY_CAR = "select s from Subscription s "
			+ "where (s.model = :m) and (s.location = :l)";

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Subscription> findByCar(Car car) {
		TypedQuery<Subscription> query = entityManager.createQuery(FIND_BY_CAR, Subscription.class)
				.setParameter("m", car.getModel())
				.setParameter("l", car.getLocation());

		return query.getResultList();
	}

}
