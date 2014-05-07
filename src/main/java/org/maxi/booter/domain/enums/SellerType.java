package org.maxi.booter.domain.enums;

public enum SellerType {

	OWNER("OWNER"), COMPANY("COMPANY");

	private SellerType(final String text) {
		this.text = text;
	}

	private final String text;

	@Override
	public String toString() {
		return text;
	}

}
