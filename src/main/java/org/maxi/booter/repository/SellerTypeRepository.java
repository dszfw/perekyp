package org.maxi.booter.repository;

import org.maxi.booter.domain.car.SellerType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SellerTypeRepository extends CrudRepository<SellerType, Long> {

}
