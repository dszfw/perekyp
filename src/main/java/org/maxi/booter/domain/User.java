package org.maxi.booter.domain;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class User extends AbstractPersistable<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private String name;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private Collection<Subscription> subscriptions;

	// Getters and Setters methods -----------------------------------------

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<Subscription> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(Collection<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}

}
