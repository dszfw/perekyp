package org.maxi.booter.domain.car;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class BodyType extends AbstractPersistable<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private String name;

	// Getters and Setters methods -----------------------------------------

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
