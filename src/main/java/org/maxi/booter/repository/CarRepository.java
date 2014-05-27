package org.maxi.booter.repository;

import org.maxi.booter.domain.Location;
import org.maxi.booter.domain.car.BodyType;
import org.maxi.booter.domain.car.Car;
import org.maxi.booter.domain.car.CarModel;
import org.maxi.booter.domain.car.EngineType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CarRepository extends PagingAndSortingRepository<Car, Long> {

	// TODO
	@Query("select c from Car c where (c.model=:model or :model is null)"
			+ "and (c.location=:location or :location is null)"
			+ "and (c.bodyType=:bodyType or :bodyType is null)"
			+ "and (c.engineType=:engineType or :engineType is null)")
	Page<Car> findByParams (@Param("model") CarModel model,
			@Param("location") Location location,
			@Param("bodyType") BodyType bodyType,
			@Param("engineType") EngineType engineType,
			Pageable pageable);
	
}
