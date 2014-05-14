package org.maxi.booter.repository;

import org.maxi.booter.domain.car.BodyType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BodyTypeRepository extends CrudRepository<BodyType, Long> {

}
