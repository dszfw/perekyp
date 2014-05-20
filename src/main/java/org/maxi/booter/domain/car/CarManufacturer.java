package org.maxi.booter.domain.car;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.maxi.booter.domain.AbstractEntity;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
//public class CarManufacturer extends AbstractPersistable<Long> {
public class CarManufacturer extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy = "manufacturer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<CarModel> models;

	@Column(nullable = false)
	private String name;

	// Getters and Setters methods -----------------------------------------

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<CarModel> getModels() {
		return models;
	}

	public void setModels(Set<CarModel> models) {
		this.models = models;
	}

}
