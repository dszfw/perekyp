package org.maxi.booter.repository;

import java.util.List;

import org.maxi.booter.domain.Location;
import org.maxi.booter.domain.Subscription;
import org.maxi.booter.domain.car.CarModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SubscriptionRepository extends CrudRepository<Subscription, Long> {

	@Query("select s from Subscription s where (s.definition.model = :m) and (s.definition.location = :l)")
	public List<Subscription> findByCarDefinitionParameters(
			@Param(value = "m") CarModel model,
			@Param(value = "l") Location location
	);
	
}
