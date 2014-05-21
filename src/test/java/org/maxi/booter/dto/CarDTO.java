package org.maxi.booter.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

// TODO Look Spring HATEOAS Resource classes
@JsonInclude(Include.NON_NULL)
public class CarDTO {

	public String modification;
	public String model;
	public String location;
	public String bodyType;
	public String engineType;
	public String transmission;
	public String drive;
	public String sellerType;
	public String createdDate;
	public String year;
	public String siteId;
	public Long mileage;
	public String color;
	public String phone;
	public Long price;
	public Long displacement;

}
