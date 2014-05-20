package org.maxi.booter.repository.subscription;

import java.util.List;

import org.maxi.booter.domain.Subscription;
import org.maxi.booter.domain.car.Car;

public interface SubscriptionRepositoryCustom {

	public List<Subscription> findByCar(Car car);

}
