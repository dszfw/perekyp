package org.maxi.booter.domain;

import org.springframework.data.jpa.domain.AbstractPersistable;

public class AbstractEntity extends AbstractPersistable<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7187508529057804068L;

	public void setId(Long id) {
		super.setId(id);
	}

}
