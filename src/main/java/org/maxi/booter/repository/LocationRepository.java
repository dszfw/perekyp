package org.maxi.booter.repository;

import org.maxi.booter.domain.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface LocationRepository extends CrudRepository<Location, Long> {

}
