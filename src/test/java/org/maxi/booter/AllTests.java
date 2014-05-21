package org.maxi.booter;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.maxi.booter.repository.BodyTypeRepositoryTest;
import org.maxi.booter.repository.CarRepositoryTest;
import org.maxi.booter.repository.CarSubscriptionRelationshipTest;
import org.maxi.booter.repository.EngineTypeRepositoryTest;
import org.maxi.booter.repository.SubscriptionRepositoryTest;
import org.maxi.booter.web.CarRestTest;
import org.maxi.booter.web.EngineTypeRestTest;

@RunWith(Suite.class)
@SuiteClasses({ BodyTypeRepositoryTest.class, CarRepositoryTest.class,
		EngineTypeRepositoryTest.class, SubscriptionRepositoryTest.class,
		CarSubscriptionRelationshipTest.class, CarRestTest.class,
		EngineTypeRestTest.class })
public class AllTests {

}
