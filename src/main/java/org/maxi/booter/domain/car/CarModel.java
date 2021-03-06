package org.maxi.booter.domain.car;

import java.util.Set;

import static javax.persistence.FetchType.*;
import static javax.persistence.CascadeType.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.maxi.booter.domain.AbstractEntity;
import org.maxi.booter.domain.Subscription;

@Entity
public class CarModel extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(optional = false, fetch = LAZY)
	private CarManufacturer manufacturer;

	@OneToMany(mappedBy = "model", fetch = LAZY, cascade = ALL)
	private Set<Car> cars;

	@OneToMany(mappedBy = "model", fetch = LAZY, cascade = ALL)
	private Set<Subscription> subscriptions;

	@Column(nullable = false)
	private String name;

	// Getters and Setters methods -----------------------------------------

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CarManufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(CarManufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	public Set<Car> getCars() {
		return cars;
	}

	public void setCars(Set<Car> cars) {
		this.cars = cars;
	}

	public Set<Subscription> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(Set<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}

}
