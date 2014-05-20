package org.maxi.booter.domain.car;

import java.util.Set;

import static javax.persistence.FetchType.*;
import static javax.persistence.CascadeType.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.maxi.booter.domain.AbstractEntity;

@Entity
public class CarManufacturer extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy = "manufacturer", fetch = LAZY, cascade = ALL)
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
