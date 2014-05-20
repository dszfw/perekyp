package org.maxi.booter.domain;

import java.util.Set;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.maxi.booter.domain.car.Car;

@Entity
public class Location extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy = "location", fetch = LAZY, cascade = ALL)
	private Set<Car> cars;
	
	@OneToMany(mappedBy = "location", fetch = LAZY, cascade = ALL)
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
