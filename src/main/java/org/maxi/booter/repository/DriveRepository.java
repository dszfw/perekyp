package org.maxi.booter.repository;

import org.maxi.booter.domain.car.Drive;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface DriveRepository extends CrudRepository<Drive, Long> {

}
