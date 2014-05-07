package org.maxi.booter.domain.car;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.annotations.Type;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Car extends AbstractPersistable<Long> {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	private CarDefinition definition;

	private String modification;

	@Type(type = "java.sql.Date")
	private LocalDate year;

	@Column(nullable = false)
	@Type(type = "java.sql.Timestamp")
	private LocalDateTime createdDate;

	private String mileage;

	private String color;

	private String phone;

	private Long price;

	@Column(nullable = false)
	private String siteId;

	private Long displacement;

	// Getters and Setters methods -----------------------------------------

	public String getModification() {
		return modification;
	}

	public void setModification(String modification) {
		this.modification = modification;
	}

	public LocalDate getYear() {
		return year;
	}

	public void setYear(LocalDate year) {
		this.year = year;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public String getMileage() {
		return mileage;
	}

	public void setMileage(String mileage) {
		this.mileage = mileage;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public Long getDisplacement() {
		return displacement;
	}

	public void setDisplacement(Long displacement) {
		this.displacement = displacement;
	}

	public CarDefinition getDefinition() {
		return definition;
	}

	public void setDefinition(CarDefinition definition) {
		this.definition = definition;
	}

}
