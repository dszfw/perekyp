package org.maxi.booter.repository.subscription;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.maxi.booter.domain.Subscription;
import org.maxi.booter.domain.car.CarDefinition;

public class SubscriptionRepositoryImpl implements SubscriptionRepositoryCustom {

	private static final String FIND_BY_CAR_DEFINITION = "select s from Subscription s "
			+ "where (s.definition.model = :m) and (s.definition.location = :l)";

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Subscription> findByCarDefinition(CarDefinition definition) {
		TypedQuery<Subscription> query = entityManager
				.createQuery(FIND_BY_CAR_DEFINITION, Subscription.class)
				.setParameter("m", definition.getModel())
				.setParameter("l", definition.getLocation());

		return query.getResultList();
	}

}
