package org.maxi.booter.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.hateoas.ResourceSupport;

public class CarResource extends ResourceSupport {

	public String modification;
	public String model;
	public String manufacturer;
	public String location;
	public String bodyType;
	public String engineType;
	public String transmission;
	public String drive;
	public String sellerType;
	public LocalDateTime createdDate;
	public LocalDate year;
	public String siteId;
	public Long mileage;
	public String color;
	public String phone;
	public Long price;
	public Long displacement;
	
}
