package org.maxi.booter.repository;

import java.time.LocalDate;

import org.maxi.booter.domain.Location;
import org.maxi.booter.domain.car.BodyType;
import org.maxi.booter.domain.car.Car;
import org.maxi.booter.domain.car.CarManufacturer;
import org.maxi.booter.domain.car.CarModel;
import org.maxi.booter.domain.car.Drive;
import org.maxi.booter.domain.car.EngineType;
import org.maxi.booter.domain.car.SellerType;
import org.maxi.booter.domain.car.Transmission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@RepositoryRestResource
public interface CarRepository extends PagingAndSortingRepository<Car, Long> {

	// TODO
	@Query("select c from Car c where"
			+ "(c.model.manufacturer=:carManufacturer or :carManufacturer is null)"
			+ "and (c.model=:carModel or :carModel is null)"
			+ "and (c.location=:location or :location is null)"
			+ "and (c.bodyType=:bodyType or :bodyType is null)"
			+ "and (c.engineType=:engineType or :engineType is null)"
			+ "and (c.transmission=:transmission or :transmission is null)"
			+ "and (c.drive=:drive or :drive is null)"
			+ "and (c.sellerType=:sellerType or :sellerType is null)"
			+ "and (c.displacement >= :displacementFrom or :displacementFrom is null)"
			+ "and (c.displacement <= :displacementTo or :displacementTo is null)"
			+ "and (c.mileage >= :mileageFrom or :mileageFrom is null)"
			+ "and (c.mileage <= :mileageTo or :mileageTo is null)"
			+ "and (c.price >= :priceFrom or :priceFrom is null)"
			+ "and (c.price <= :priceTo or :priceTo is null)"
			+ "and (c.year >= :yearFrom or :yearFrom is null)"
			+ "and (c.year <= :yearTo or :yearTo is null)")
	Page<Car> findByParams (@Param("carManufacturer") CarManufacturer carManufacturer,
			@Param("carModel") CarModel carModel,
			@Param("location") Location location,
			@Param("bodyType") BodyType bodyType,
			@Param("engineType") EngineType engineType,
			@Param("transmission") Transmission transmission,
			@Param("drive") Drive drive,
			@Param("sellerType") SellerType sellerType,
			@Param("displacementFrom") Long displacementFrom,
			@Param("displacementTo") Long displacementTo,
			@Param("mileageFrom") Long mileageFrom,
			@Param("mileageTo") Long mileageTo,
			@Param("priceFrom") Long priceFrom,
			@Param("priceTo") Long priceTo,
			@Param("yearFrom") @DateTimeFormat(iso = ISO.DATE) LocalDate yearFrom,
			@Param("yearTo") @DateTimeFormat(iso = ISO.DATE) LocalDate yearTo,
			Pageable pageable);
	
}
