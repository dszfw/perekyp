package org.maxi.booter.domain;

import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.maxi.booter.domain.car.Car;
import org.maxi.booter.domain.car.CarDefinition;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Subscription extends AbstractPersistable<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private User user;

	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
	@JoinTable(
			name = "cars_subscriptions",
			inverseJoinColumns = @JoinColumn(name = "car_id"),
			joinColumns = @JoinColumn(name = "subscription_id")
	)
	private List<Car> cars;

	private CarDefinition definition;

	@Column(nullable = false)
	private String name;

	@Temporal(TemporalType.DATE)
	private Calendar yearFrom;

	@Temporal(TemporalType.DATE)
	private Calendar yearTo;

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

	public CarDefinition getDefinition() {
		return definition;
	}

	public void setDefinition(CarDefinition definition) {
		this.definition = definition;
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

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	public Calendar getYearFrom() {
		return yearFrom;
	}

	public void setYearFrom(Calendar yearFrom) {
		this.yearFrom = yearFrom;
	}

	public Calendar getYearTo() {
		return yearTo;
	}

	public void setYearTo(Calendar yearTo) {
		this.yearTo = yearTo;
	}

}
