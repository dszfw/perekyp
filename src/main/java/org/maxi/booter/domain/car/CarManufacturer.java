package org.maxi.booter.domain.car;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class CarManufacturer extends AbstractPersistable<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private String name;

	@OneToMany(mappedBy = "manufacturer", fetch = FetchType.LAZY)
	private Collection<CarModel> models;

	// Getters and Setters methods -----------------------------------------

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<CarModel> getModels() {
		return models;
	}

	public void setModels(Collection<CarModel> models) {
		this.models = models;
	}

}
