package org.maxi.booter.repository.subscription;

import org.maxi.booter.domain.Subscription;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SubscriptionRepository extends CrudRepository<Subscription, Long>, SubscriptionRepositoryCustom {

}
