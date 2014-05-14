package org.maxi.booter.domain.car;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.maxi.booter.domain.Subscription;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Transmission extends AbstractPersistable<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy = "definition.transmission", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Car> cars;

	@OneToMany(mappedBy = "definition.transmission", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Subscription> subscriptions;

	@Column(nullable = false)
	private String name;

	// Getters and Setters methods -----------------------------------------

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	public List<Subscription> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(List<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
