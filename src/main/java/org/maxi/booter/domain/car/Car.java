package org.maxi.booter.domain.car;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.maxi.booter.domain.Subscription;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Car extends AbstractPersistable<Long> {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
	@JoinTable(
			name = "cars_subscriptions",
			joinColumns = @JoinColumn(name = "car_id"),
			inverseJoinColumns = @JoinColumn(name = "subscription_id")
	)
	private Set<Subscription> subscriptions = new HashSet<>();

	private CarDefinition definition;

	private String modification;

	@Temporal(TemporalType.DATE)
	private Calendar year;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar createdDate;

	private String mileage;

	private String color;

	private String phone;

	private Long price;

	@Column(nullable = false)
	private String siteId;

	private Long displacement;

	@Column(columnDefinition = "boolean default false")
	private boolean processed;

	// Getters and Setters methods -----------------------------------------

	public String getModification() {
		return modification;
	}

	public void setModification(String modification) {
		this.modification = modification;
	}

	public Calendar getYear() {
		return year;
	}

	public void setYear(Calendar year) {
		this.year = year;
	}

	public Calendar getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Calendar createdDate) {
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

	public Set<Subscription> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(Set<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}

	public boolean isProcessed() {
		return processed;
	}

	public void setProcessed(boolean processed) {
		this.processed = processed;
	}

}
