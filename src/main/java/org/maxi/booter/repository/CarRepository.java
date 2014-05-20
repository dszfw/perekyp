package org.maxi.booter.repository;

import java.util.List;

import org.maxi.booter.domain.car.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CarRepository extends CrudRepository<Car, Long> {

	public List<Car> findByProcessed(boolean processed);
	
}
