package org.maxi.booter.repository.subscription;

import java.util.List;

import org.maxi.booter.domain.Subscription;
import org.maxi.booter.domain.car.CarDefinition;

public interface SubscriptionRepositoryCustom {

	public List<Subscription> findByCarDefinition(CarDefinition definition);

}
