package org.maxi.booter.repository;

import org.maxi.booter.domain.car.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CarRepostitory extends CrudRepository<Car, Long> {

}
