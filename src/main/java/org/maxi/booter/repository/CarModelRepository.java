package org.maxi.booter.repository;

import org.maxi.booter.domain.car.CarModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CarModelRepository extends CrudRepository<CarModel, Long> {

}
