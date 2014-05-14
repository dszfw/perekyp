package org.maxi.booter.repository;

import org.maxi.booter.domain.car.EngineType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface EngineTypeRepository extends CrudRepository<EngineType, Long> {

}
