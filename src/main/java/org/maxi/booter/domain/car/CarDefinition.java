package org.maxi.booter.domain.car;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import org.maxi.booter.domain.Location;

@Embeddable
public class CarDefinition implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private CarModel model;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Location location;

	@ManyToOne(fetch = FetchType.LAZY)
	private BodyType bodyType;

	@ManyToOne(fetch = FetchType.LAZY)
	private EngineType engineType;

	@ManyToOne(fetch = FetchType.LAZY)
	private Transmission transmission;

	@ManyToOne(fetch = FetchType.LAZY)
	private Drive drive;

	@ManyToOne(fetch = FetchType.LAZY)
	private SellerType sellerType;

	// Getters and Setters methods -----------------------------------------

	public CarModel getModel() {
		return model;
	}

	public void setModel(CarModel model) {
		this.model = model;
	}

	public BodyType getBodyType() {
		return bodyType;
	}

	public void setBodyType(BodyType bodyType) {
		this.bodyType = bodyType;
	}

	public EngineType getEngineType() {
		return engineType;
	}

	public void setEngineType(EngineType engineType) {
		this.engineType = engineType;
	}

	public Transmission getTransmission() {
		return transmission;
	}

	public void setTransmission(Transmission transmission) {
		this.transmission = transmission;
	}

	public Drive getDrive() {
		return drive;
	}

	public void setDrive(Drive drive) {
		this.drive = drive;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public SellerType getSellerType() {
		return sellerType;
	}

	public void setSellerType(SellerType seller) {
		this.sellerType = seller;
	}

}
