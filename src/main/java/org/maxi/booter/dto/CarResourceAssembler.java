package org.maxi.booter.dto;

import org.maxi.booter.domain.Location;
import org.maxi.booter.domain.car.BodyType;
import org.maxi.booter.domain.car.Car;
import org.maxi.booter.domain.car.CarManufacturer;
import org.maxi.booter.domain.car.CarModel;
import org.maxi.booter.domain.car.Drive;
import org.maxi.booter.domain.car.EngineType;
import org.maxi.booter.domain.car.SellerType;
import org.maxi.booter.domain.car.Transmission;
import org.maxi.booter.web.CarLightController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class CarResourceAssembler extends ResourceAssemblerSupport<Car, CarResource>{

	@Autowired
	private EntityLinks eLinks;
	
	public CarResourceAssembler() {
		super(CarLightController.class, CarResource.class);
	}

	@Override
	public CarResource toResource(Car entity) {
		CarResource resource = createResourceWithId(entity.getId(), entity);
		resource.modification = entity.getModification();
		resource.createdDate = entity.getCreatedDate();
		resource.year = entity.getYear();
		resource.siteId = entity.getSiteId();
		resource.mileage = entity.getMileage();
		resource.color = entity.getColor();
		resource.phone = entity.getPhone();
		resource.price = entity.getPrice();
		resource.displacement = entity.getDisplacement();
		
		CarModel model = entity.getModel();
		if (model != null) {
			resource.model = model.getName();
			CarManufacturer manufacturer = model.getManufacturer();
			if (manufacturer != null)
				resource.manufacturer = manufacturer.getName();
		}
		
		Location location = entity.getLocation();
		if (location != null) resource.location = location.getName();
		
		BodyType bodyType = entity.getBodyType();
		if (bodyType != null) resource.bodyType = bodyType.getName();
		
		EngineType engineType = entity.getEngineType();
		if (engineType != null) resource.engineType = engineType.getName();
		
		Transmission transmission = entity.getTransmission();
		if (transmission != null) resource.transmission = transmission.getName();
		
		Drive drive = entity.getDrive();
		if (drive != null) resource.drive = drive.getName();
		
		SellerType sellerType = entity.getSellerType();
		if (sellerType != null) resource.sellerType = sellerType.getName();
		
		resource.add(eLinks.linkToSingleResource(Car.class, entity.getId()));
		
		return resource;
	}

}
