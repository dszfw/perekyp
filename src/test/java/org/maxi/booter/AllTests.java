package org.maxi.booter;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.maxi.booter.repository.BodyTypeRepositoryTest;
import org.maxi.booter.repository.CarRepositoryTest;
import org.maxi.booter.repository.CarSubscriptionRelationshipTest;
import org.maxi.booter.repository.EngineTypeRepositoryTest;
import org.maxi.booter.repository.SubscriptionRepositoryTest;
import org.maxi.booter.web.CarDataRestTest;
import org.maxi.booter.web.CarLightControllerTest;
import org.maxi.booter.web.EngineTypeDataRestTest;

@RunWith(Suite.class)
@SuiteClasses({ BodyTypeRepositoryTest.class, CarRepositoryTest.class,
		EngineTypeRepositoryTest.class, SubscriptionRepositoryTest.class,
		CarSubscriptionRelationshipTest.class, CarDataRestTest.class,
		EngineTypeDataRestTest.class, CarLightControllerTest.class })
public class AllTests {

}
