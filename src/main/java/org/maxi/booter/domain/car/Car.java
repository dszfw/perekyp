package org.maxi.booter.domain.car;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.FetchType.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;
import org.maxi.booter.domain.AbstractEntity;
import org.maxi.booter.domain.Location;
import org.maxi.booter.domain.Subscription;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Car extends AbstractEntity {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	@ManyToMany(fetch = LAZY, cascade = {CascadeType.PERSIST})
	@JoinTable(
			name = "cars_subscriptions",
			joinColumns = @JoinColumn(name = "car_id"),
			inverseJoinColumns = @JoinColumn(name = "subscription_id"))
	private Set<Subscription> subscriptions = new HashSet<>();

	private String modification;

	@ManyToOne(optional = false, fetch = LAZY)
	private CarModel model;

	@ManyToOne(optional = false, fetch = LAZY)
	private Location location;

	@ManyToOne(fetch = LAZY)
	private BodyType bodyType;

	@ManyToOne(fetch = LAZY)
	private EngineType engineType;

	@ManyToOne(fetch = LAZY)
	private Transmission transmission;

	@ManyToOne(fetch = LAZY)
	private Drive drive;

	@ManyToOne(fetch = LAZY)
	private SellerType sellerType;
	
	@Column(nullable = false)
	@Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalDateTime")
	private LocalDateTime createdDate;
	
	@Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
	private LocalDate year;
	
	@Column(nullable = false, unique = true)
	private String siteId;
	
	private Long mileage;

	private String color;

	private String phone;

	private Long price;

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

	// TODO
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public Long getMileage() {
		return mileage;
	}

	public void setMileage(Long mileage) {
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

	public CarModel getModel() {
		return model;
	}

	public void setModel(CarModel model) {
		this.model = model;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public BodyType getBodyType() {
		return bodyType;
	}

	public void setBodyType(BodyType bodyType) {
		this.bodyType = bodyType;
	}

	public EngineType getEngineType() {
		return engineType;
	}

	public void setEngineType(EngineType engineType) {
		this.engineType = engineType;
	}

	public Transmission getTransmission() {
		return transmission;
	}

	public void setTransmission(Transmission transmission) {
		this.transmission = transmission;
	}

	public Drive getDrive() {
		return drive;
	}

	public void setDrive(Drive drive) {
		this.drive = drive;
	}

	public SellerType getSellerType() {
		return sellerType;
	}

	public void setSellerType(SellerType sellerType) {
		this.sellerType = sellerType;
	}

	public Set<Subscription> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(Set<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}

}
