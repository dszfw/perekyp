package org.maxi.booter.domain.car.enums;

public enum Transmission {

	AUTOMATIC("AUTOMATIC"), MECHANIC("MECHANIC");

	private Transmission(final String text) {
		this.text = text;
	}

	private final String text;

	@Override
	public String toString() {
		return text;
	}

}
