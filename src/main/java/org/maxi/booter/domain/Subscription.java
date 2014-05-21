package org.maxi.booter.domain;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.FetchType.*;
import static javax.persistence.CascadeType.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;
import org.maxi.booter.domain.car.BodyType;
import org.maxi.booter.domain.car.Car;
import org.maxi.booter.domain.car.CarModel;
import org.maxi.booter.domain.car.Drive;
import org.maxi.booter.domain.car.EngineType;
import org.maxi.booter.domain.car.SellerType;
import org.maxi.booter.domain.car.Transmission;

@Entity
public class Subscription extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(optional = false)
	private User user;

	@ManyToMany(fetch = LAZY, cascade = {PERSIST})
	@JoinTable(
			name = "cars_subscriptions",
			inverseJoinColumns = @JoinColumn(name = "car_id"),
			joinColumns = @JoinColumn(name = "subscription_id"))
	private Set<Car> cars = new HashSet<Car>();

	@ManyToOne(optional = false, fetch = LAZY)
	private CarModel model;

	@ManyToOne(optional = false, fetch = LAZY)
	private Location location;

	@ManyToOne(fetch = LAZY)
	private BodyType bodyType;

	@ManyToOne(fetch = LAZY)
	private EngineType engineType;

	@ManyToOne(fetch = LAZY)
	private Transmission transmission;

	@ManyToOne(fetch = LAZY)
	private Drive drive;

	@ManyToOne(fetch = LAZY)
	private SellerType sellerType;

	@Column(nullable = false)
	private String name;

	@Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
	private LocalDate yearFrom;

	@Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
	private LocalDate yearTo;

	private Long mileageFrom;

	private Long mileageTo;

	private Long priceFrom;

	private Long priceTo;

	// Getters and Setters methods -----------------------------------------

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public CarModel getModel() {
		return model;
	}

	public void setModel(CarModel model) {
		this.model = model;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
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

	public SellerType getSellerType() {
		return sellerType;
	}

	public void setSellerType(SellerType sellerType) {
		this.sellerType = sellerType;
	}

	public Long getMileageFrom() {
		return mileageFrom;
	}

	public void setMileageFrom(Long mileageFrom) {
		this.mileageFrom = mileageFrom;
	}

	public Long getMileageTo() {
		return mileageTo;
	}

	public void setMileageTo(Long mileageTo) {
		this.mileageTo = mileageTo;
	}

	public Long getPriceFrom() {
		return priceFrom;
	}

	public void setPriceFrom(Long priceFrom) {
		this.priceFrom = priceFrom;
	}

	public Long getPriceTo() {
		return priceTo;
	}

	public void setPriceTo(Long priceTo) {
		this.priceTo = priceTo;
	}

	public Set<Car> getCars() {
		return cars;
	}

	public void setCars(Set<Car> cars) {
		this.cars = cars;
	}

	public LocalDate getYearFrom() {
		return yearFrom;
	}

	public void setYearFrom(LocalDate yearFrom) {
		this.yearFrom = yearFrom;
	}

	public LocalDate getYearTo() {
		return yearTo;
	}

	public void setYearTo(LocalDate yearTo) {
		this.yearTo = yearTo;
	}

}
