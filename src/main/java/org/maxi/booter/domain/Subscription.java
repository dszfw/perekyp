package org.maxi.booter.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;
import org.maxi.booter.domain.car.CarDefinition;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Subscription extends AbstractPersistable<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private String name;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private User user;

	private CarDefinition carDefinition;

	@Type(type = "java.sql.Date")
	private LocalDate yearFrom;

	@Type(type = "java.sql.Date")
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

	public CarDefinition getCarDefinition() {
		return carDefinition;
	}

	public void setCarDefinition(CarDefinition carDefinition) {
		this.carDefinition = carDefinition;
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

}
