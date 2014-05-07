package org.maxi.booter.domain.car.enums;

public enum Drive {

	FWD("FRONT"), RWD("REAR"), AWD("4WD");

	private Drive(final String text) {
		this.text = text;
	}

	private final String text;

	@Override
	public String toString() {
		return text;
	}

}
