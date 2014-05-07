package org.maxi.booter.domain.car;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class CarModel extends AbstractPersistable<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private String name;

	@ManyToOne(optional = false)
	private CarManufacturer manufacturer;
	
	@OneToMany(mappedBy = "definition.model", fetch = FetchType.LAZY)
	private Collection<Car> cars;

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

	public Collection<Car> getCars() {
		return cars;
	}

	public void setCars(Collection<Car> cars) {
		this.cars = cars;
	}

}
