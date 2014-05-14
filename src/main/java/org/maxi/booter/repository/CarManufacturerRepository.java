package org.maxi.booter.repository;

import org.maxi.booter.domain.car.CarManufacturer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CarManufacturerRepository extends CrudRepository<CarManufacturer, Long> {

}
