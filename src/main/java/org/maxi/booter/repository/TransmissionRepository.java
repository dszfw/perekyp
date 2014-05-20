package org.maxi.booter.repository;

import org.maxi.booter.domain.car.Transmission;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TransmissionRepository extends CrudRepository<Transmission, Long> {

}
